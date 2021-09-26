package com.wpi.android.basketballcounter

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "ScoreViewModel"

class ScoreViewModel : ViewModel() {

    init {
        Log.d(TAG, "ViewModel instance created")
    }

    var score_A = 0
    var score_B = 0


    fun addPoints(pointsNum: Int, flag: Int)  {
        if (flag==0){
            score_A+=pointsNum
            Log.d(TAG, "Get a score for team A $pointsNum")
        }else{
            score_B+=pointsNum
            Log.d(TAG, "Get a score for team B $pointsNum")
        }
    }



    fun reset(){
        Log.d(TAG, "Points reset")
        score_A = 0
        score_B = 0

    }

}