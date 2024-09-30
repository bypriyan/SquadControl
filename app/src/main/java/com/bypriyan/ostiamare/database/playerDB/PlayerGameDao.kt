package com.bypriyan.ostiamare.database.playerDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bypriyan.ostiamare.model.PlayerGame

@Dao
interface PlayerGameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayerGame(playerGame: PlayerGame)

    @Query("SELECT * FROM player_game_table WHERE gameId = :gameId")
    suspend fun getPlayersForGame(gameId: Int): List<PlayerGame>
}