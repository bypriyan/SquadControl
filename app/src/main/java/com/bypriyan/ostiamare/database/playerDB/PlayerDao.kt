package com.bypriyan.ostiamare.database.playerDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bypriyan.ostiamare.model.ModelPlayer

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlayer(modelPlayer: ModelPlayer)

    @Query("SELECT * FROM player_table ORDER BY id ASC")
    fun getAllPlayer(): LiveData<List<ModelPlayer>>

    @Query("SELECT * FROM player_table WHERE id = :playerId")
    suspend fun getPlayerById(playerId: Int): ModelPlayer

    @Delete
    suspend fun deletePlayer(player: ModelPlayer)

    @Query("SELECT * FROM player_table")
    fun getAllPlayersList(): List<ModelPlayer> // Synchronous list query

    // Add this update method
    @Update
    suspend fun updatePlayer(modelPlayer: ModelPlayer)

}