package com.wpi.android.basketballcounter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val SCORE_A=
    "com.bignerdranch.android.bsktblct.scoreA"
private const val SCORE_B=
    "com.bignerdranch.android.bsktblct.scoreB"
private const val FOUL_A=
    "com.bignerdranch.android.bsktblct.foulA"
private const val FOUL_B=
    "com.bignerdranch.android.bsktblct.foulB"

//private const val BUNDLE=
//    "com.bignerdranch.android.bsktblct.bundle"

const val NICE_MATCH = "com.bignerdranch.android.bsktblct.nice_match"



class SaveActivity : AppCompatActivity() {
    private lateinit var boardLS: TextView
    private lateinit var FboardLS: TextView

    private lateinit var boardRS: TextView
    private lateinit var FboardRS: TextView

    private lateinit var niceBtn: Button

    private var score_A = 0
    private var score_B = 0
    private var foul_A = 0
    private var foul_B = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        score_A = intent.getIntExtra(SCORE_A,0)
        score_B = intent.getIntExtra(SCORE_B,0)
        foul_A = intent.getIntExtra(FOUL_A,0)
        foul_B = intent.getIntExtra(FOUL_B,0)

        boardLS = findViewById(R.id.score_A_s)
        FboardLS = findViewById(R.id.foul_A_s)
        boardRS = findViewById(R.id.score_B_s)
        FboardRS = findViewById(R.id.foul_B_s)

        niceBtn = findViewById(R.id.nice_match)

        boardLS.text = score_A.toString()
        FboardLS.text = foul_A.toString()
        boardRS.text = score_B.toString()
        FboardRS.text = foul_B.toString()

        niceBtn.setOnClickListener{
            setNiceMatch(true)
        }

    }

    private fun setNiceMatch(isNice: Boolean) {
        val data = Intent().apply {
            putExtra(NICE_MATCH, isNice)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        //make it easy for other code to properly configure their launching intents
        fun newIntent(packageContext: Context, match:Match): Intent {
//            info.putInt(SCORE_A, match.score_A)
//            info.putInt(SCORE_B, match.score_B)
//            info.putInt(FOUL_A, match.foul_A)
//            info.putInt(FOUL_B, match.foul_B)
            return Intent(packageContext, SaveActivity::class.java).apply {
                   // putExtra(BUNDLE,info)
                //putExtra(SCORE_A,match.score_A)
                putExtra(SCORE_A, match.score_A)
                putExtra(SCORE_B, match.score_B)
                putExtra(FOUL_A, match.foul_A)
                putExtra(FOUL_B, match.foul_B)
            }
        }
    }
}