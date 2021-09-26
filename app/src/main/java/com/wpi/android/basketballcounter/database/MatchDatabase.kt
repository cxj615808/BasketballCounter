package com.wpi.android.basketballcounter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wpi.android.basketballcounter.Game

@Database(entities = [Game::class], version = 1)
@TypeConverters(MatchTypeConverters::class)
abstract class MatchDatabase  : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}