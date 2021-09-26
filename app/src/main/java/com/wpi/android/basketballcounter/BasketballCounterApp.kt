package com.wpi.android.basketballcounter

import android.app.Application

class BasketballCounterApp : Application()  {

    override fun onCreate() {
        super.onCreate()
        MatchRepository.initialize(this)
    }
}