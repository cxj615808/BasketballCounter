package com.wpi.android.basketballcounter.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wpi.android.basketballcounter.Game
import java.util.*

@Dao
interface MatchDao {

    @Query("SELECT * FROM game")
    fun getMatchs(): LiveData<List<Game>>

    @Query("SELECT * FROM game WHERE id=(:id)")
    fun getMatch(id: UUID): LiveData<Game?>

    @Insert
    fun addMatch(game: Game)
}