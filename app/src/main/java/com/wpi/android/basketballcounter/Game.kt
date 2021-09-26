package com.wpi.android.basketballcounter

import androidx.room.Entity
import androidx.room.PrimaryKey

import java.util.*

@Entity
data class Game(@PrimaryKey val id: UUID = UUID.randomUUID(),
                var title: String = "",
                var score_A: Int = 0,
                var score_B: Int = 0,
                var date: Date = Date(),
                var teamA: String = "",
                var teamB: String = "",
                 )