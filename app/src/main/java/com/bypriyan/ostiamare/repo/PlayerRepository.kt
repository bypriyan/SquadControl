package com.bypriyan.ostiamare.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.bypriyan.ostiamare.database.playerDB.PlayerDao
import com.bypriyan.ostiamare.model.ModelPlayer
import javax.inject.Inject


class PlayerRepository @Inject constructor(private val playerDao: PlayerDao){

    val allPlayers: LiveData<List<ModelPlayer>> = playerDao.getAllPlayer()

    suspend fun getPlayerById(id:Int): ModelPlayer {
        return playerDao.getPlayerById(id) // Synchronous list query
    }

    suspend fun insert(player: ModelPlayer): Boolean {
        return try {
            playerDao.addPlayer(player) // This method should be a suspend function
            true
        } catch (e: Exception) {
            Log.d("TAG", "insert: ${e.message}")
            false
        }
    }

    fun getAllPlayersList(): List<ModelPlayer> {
        return playerDao.getAllPlayersList() // Synchronous list query
    }

    // Add this update function
    suspend fun updatePlayer(player: ModelPlayer): Boolean {
        return try {
            playerDao.updatePlayer(player)
            true
        } catch (e: Exception) {
            Log.d("TAG", "update: ${e.message}")
            false
        }
    }
}