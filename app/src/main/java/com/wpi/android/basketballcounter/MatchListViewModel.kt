package com.wpi.android.basketballcounter

import androidx.lifecycle.ViewModel

class MatchListViewModel : ViewModel() {

    private val matchRepository = MatchRepository.get()
    val matchListLiveData = matchRepository.getMatchs()
}