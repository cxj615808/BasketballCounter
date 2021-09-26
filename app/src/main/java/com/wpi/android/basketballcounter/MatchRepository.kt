package com.wpi.android.basketballcounter

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.wpi.android.basketballcounter.database.MatchDatabase
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "game-database"

class MatchRepository private constructor(context: Context) {

    private val database : MatchDatabase = Room.databaseBuilder(
        context.applicationContext,
        MatchDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val matchDao = database.matchDao()
    private val executor = Executors.newSingleThreadExecutor() //remove before pushing

    fun getMatchs(): LiveData<List<Game>> = matchDao.getMatchs()

    fun getMatch(id: UUID): LiveData<Game?> = matchDao.getMatch(id)

    fun addMatch(game: Game) {
        executor.execute {
            matchDao.addMatch(game)
        }
    } //remove this eventually

    companion object {
        private var INSTANCE: MatchRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = MatchRepository(context)
            }
        }

        fun get(): MatchRepository {
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}