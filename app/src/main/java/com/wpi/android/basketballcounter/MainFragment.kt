package com.wpi.android.basketballcounter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainFragment"

private const val SCORE_L = "score_l"
private const val SCORE_R = "score_r"


class MainFragment : Fragment() {

    private lateinit var game: Game

    private lateinit var boardL: TextView
    private lateinit var FboardL: TextView
    private lateinit var threePL: Button
    private lateinit var twoPL: Button
    private lateinit var freeThrowL: Button


    private lateinit var boardR: TextView
    private lateinit var FboardR: TextView
    private lateinit var threePR: Button
    private lateinit var twoPR: Button
    private lateinit var freeThrowR: Button


    private lateinit var reset: Button
    private lateinit var save: Button

    private lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game = Game()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val provider: ViewModelProvider = ViewModelProviders.of(this)
        scoreViewModel = provider.get(ScoreViewModel::class.java)

        val currentScoreL = savedInstanceState?.getInt(SCORE_L, 0) ?: 0
        val currentScoreR = savedInstanceState?.getInt(SCORE_R, 0) ?: 0


        scoreViewModel.score_A = currentScoreL
        scoreViewModel.score_B = currentScoreR

        boardL = view.findViewById(R.id.score_A)
        threePL = view.findViewById(R.id.three_point_button_left)
        twoPL = view.findViewById(R.id.two_point_button_left)
        freeThrowL = view.findViewById(R.id.free_throw_left)

        boardR = view.findViewById(R.id.score_B)
        threePR = view.findViewById(R.id.three_point_button_right)
        twoPR = view.findViewById(R.id.two_point_button_right)
        freeThrowR = view.findViewById(R.id.free_throw_right)

        reset = view.findViewById(R.id.reset_button)
        save = view.findViewById(R.id.save_button)


        threePL.setOnClickListener{
            scoreViewModel.addPoints(3, 0)
            updateScore()
        }
        twoPL.setOnClickListener{
            scoreViewModel.addPoints(2, 0)
            updateScore()
        }


        threePR.setOnClickListener{
            scoreViewModel.addPoints(3, 1)
            updateScore()
        }
        twoPR.setOnClickListener{
            scoreViewModel.addPoints(2, 1)
            updateScore()
        }

        freeThrowL.setOnClickListener{
            scoreViewModel.addPoints(1, 0)
            updateScore()
        }

        freeThrowR.setOnClickListener{
            scoreViewModel.addPoints(1, 1)
            updateScore()
        }


        reset.setOnClickListener{
            boardL.text = "0"
            boardR.text = "0"
            FboardL.text = "0"
            FboardR.text = "0"
            scoreViewModel.reset()
        }

        updateScore()
        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(SCORE_L, scoreViewModel.score_A)
        savedInstanceState.putInt(SCORE_R, scoreViewModel.score_B)

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }


    private fun updateScore() {
        val scoreL = scoreViewModel.score_A
        val scoreR = scoreViewModel.score_B

        boardL.text = scoreL.toString()
        boardR.text = scoreR.toString()

    }
}