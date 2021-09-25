package com.wpi.android.basketballcounter

import androidx.lifecycle.ViewModel

class MatchListViewModel : ViewModel() {

    val matchs = mutableListOf<Match>()

    init {
        for (i in 0 until 100) {
            val match = Match()
            match.title = "Match #$i"
            matchs += match
        }
    }
}