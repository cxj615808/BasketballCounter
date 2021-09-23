package com.wpi.android.basketballcounter

import androidx.room.Entity
import androidx.room.PrimaryKey

import java.util.*

@Entity
data class Match(@PrimaryKey val id: UUID = UUID.randomUUID(),
                 var score_A: Int = 0,
                 var score_B: Int = 0,
                 var foul_A: Int = 0,
                 var foul_B: Int = 0,
                 )