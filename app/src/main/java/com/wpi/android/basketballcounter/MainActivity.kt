package com.wpi.android.basketballcounter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


private const val TAG = "MainActivity"

private const val SCORE_L = "score_l"
private const val SCORE_R = "score_r"
private const val FOUL_L = "foul_l"
private const val FOUL_R = "foul_r"

private const val REQUEST_CODE_SAVE = 0

class MainActivity : AppCompatActivity() {
    private lateinit var boardL: TextView
    private lateinit var FboardL: TextView
    private lateinit var threePL: Button
    private lateinit var twoPL: Button
    private lateinit var freeThrowL: Button
    private lateinit var FoulL: Button

    private lateinit var boardR: TextView
    private lateinit var FboardR: TextView
    private lateinit var threePR: Button
    private lateinit var twoPR: Button
    private lateinit var freeThrowR: Button
    private lateinit var FoulR: Button

    private lateinit var reset: Button
    private lateinit var save: Button


    //    private val scoreViewModel: ScoreViewModel by lazy {
//        ViewModelProviders.of(this).get(scoreViewModel::class.java)
//    }
    private lateinit var scoreViewModel: ScoreViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
//        Log.d(TAG, scoreViewModel.score_A.toString())
//        Log.d(TAG, scoreViewModel.score_B.toString())


        val provider: ViewModelProvider = ViewModelProviders.of(this)
        scoreViewModel = provider.get(ScoreViewModel::class.java)

        setContentView(R.layout.activity_main)

        val currentScoreL = savedInstanceState?.getInt(SCORE_L, 0) ?: 0
        val currentScoreR = savedInstanceState?.getInt(SCORE_R, 0) ?: 0

        val currentFoulL = savedInstanceState?.getInt(FOUL_L, 0) ?: 0
        val currentFoulR = savedInstanceState?.getInt(FOUL_R, 0) ?: 0

        scoreViewModel.score_A = currentScoreL
        scoreViewModel.score_B = currentScoreR

        scoreViewModel.foul_A = currentFoulL
        scoreViewModel.foul_B = currentFoulR

        boardL = findViewById(R.id.score_A)
        FboardL = findViewById(R.id.foul_A)
        threePL = findViewById(R.id.three_point_button_left)
        twoPL = findViewById(R.id.two_point_button_left)
        freeThrowL = findViewById(R.id.free_throw_left)
        FoulL = findViewById(R.id.foul)

        boardR = findViewById(R.id.score_B)
        FboardR = findViewById(R.id.foul_B)
        threePR = findViewById(R.id.three_point_button_right)
        twoPR = findViewById(R.id.two_point_button_right)
        freeThrowR = findViewById(R.id.free_throw_right)
        FoulR = findViewById(R.id.foul_right)

        reset = findViewById(R.id.reset_button)
        save = findViewById(R.id.save_button)


        threePL.setOnClickListener{
            scoreViewModel.addPoints(3, 0)
            updateScore()
        }
        twoPL.setOnClickListener{
            scoreViewModel.addPoints(2, 0)
            updateScore()
        }
        FoulL.setOnClickListener{
            scoreViewModel.addFouls(1,0)
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

        FoulR.setOnClickListener{
            scoreViewModel.addFouls(1,1)
            updateScore()
        }

        reset.setOnClickListener{
            boardL.text = "0"
            boardR.text = "0"
            FboardL.text = "0"
            FboardR.text = "0"
            scoreViewModel.reset()
        }

        save.setOnClickListener{ view ->
            val extras = Bundle()
            extras.putInt("S_A",scoreViewModel.score_A)
            extras.putInt("S_B",scoreViewModel.score_B)
            extras.putInt("F_A",scoreViewModel.foul_A)
            extras.putInt("F_B",scoreViewModel.foul_B)

            val intent = SaveActivity.newIntent(this@MainActivity,
                Match(
                    score_A = scoreViewModel.score_A,
                    score_B = scoreViewModel.score_B,
                    foul_A = scoreViewModel.foul_A,
                    foul_B = scoreViewModel.foul_B
                )
            )
            startActivityForResult(intent,REQUEST_CODE_SAVE);

        }

        updateScore()
    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_SAVE) {
            val isNice : Boolean = data?.getBooleanExtra(NICE_MATCH, false) ?: false
            if (isNice){
                Toast.makeText(this, R.string.nice_match_toast, Toast.LENGTH_SHORT)
                    .show()
            }
        }
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
        savedInstanceState.putInt(FOUL_L, scoreViewModel.foul_A)
        savedInstanceState.putInt(FOUL_R, scoreViewModel.foul_B)
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
        val foulL = scoreViewModel.foul_A
        val foulR = scoreViewModel.foul_B
        boardL.text = scoreL.toString()
        boardR.text = scoreR.toString()
        FboardL.text = foulL.toString()
        FboardR.text = foulR.toString()
    }
}