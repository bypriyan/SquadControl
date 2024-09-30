package com.bypriyan.ostiamare.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bypriyan.ostiamare.Class.Player

@Dao
interface PlayerDao {
    @Insert
    suspend fun insert(player: Player)

    @Update
    suspend fun update(player: Player)

    @Delete
    suspend fun delete(player: Player)

    @Query("SELECT * FROM players WHERE id = :id")
    suspend fun getPlayerById(id: Long): Player?

    @Query("SELECT * FROM players")
    suspend fun getAllPlayers(): List<Player>
}