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


//private const val BUNDLE=
//    "com.bignerdranch.android.bsktblct.bundle"

const val NICE_MATCH = "com.bignerdranch.android.bsktblct.nice_match"



class SaveActivity : AppCompatActivity() {
    private lateinit var boardLS: TextView

    private lateinit var boardRS: TextView

    private lateinit var niceBtn: Button

    private var score_A = 0
    private var score_B = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        score_A = intent.getIntExtra(SCORE_A,0)
        score_B = intent.getIntExtra(SCORE_B,0)


        boardLS = findViewById(R.id.score_A_s)
        boardRS = findViewById(R.id.score_B_s)

        niceBtn = findViewById(R.id.nice_match)

        boardLS.text = score_A.toString()
        boardRS.text = score_B.toString()

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
        fun newIntent(packageContext: Context, game:Game): Intent {

            return Intent(packageContext, SaveActivity::class.java).apply {
                   // putExtra(BUNDLE,info)
                //putExtra(SCORE_A,match.score_A)
                putExtra(SCORE_A, game.score_A)
                putExtra(SCORE_B, game.score_B)

            }
        }
    }
}