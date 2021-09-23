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
    var foul_A  = 0
    var foul_B  = 0

    fun addPoints(pointsNum: Int, flag: Int)  {
        if (flag==0){
            score_A+=pointsNum
            Log.d(TAG, "Get a score for team A $pointsNum")
        }else{
            score_B+=pointsNum
            Log.d(TAG, "Get a score for team B $pointsNum")
        }
    }

    fun addFouls(FoulNum: Int, flag: Int)  {
        if (flag==0){
            foul_A+=FoulNum
            Log.d(TAG, "Get Fouls for team A $FoulNum")
        }else{
            foul_B+=FoulNum
            Log.d(TAG, "Get Fouls for team B $FoulNum")
        }
    }

    fun reset(){
        Log.d(TAG, "Points reset")
        score_A = 0
        score_B = 0
        foul_A = 0
        foul_B = 0
    }

}