package com.bypriyan.ostiamare.repo

import androidx.lifecycle.LiveData
import com.bypriyan.ostiamare.database.playerDB.GameDao
import com.bypriyan.ostiamare.model.Game
import javax.inject.Inject

class GameRepository @Inject constructor(private val gameDao: GameDao) {

    // Fetch all games as LiveData
    val allGames: LiveData<List<Game>> = gameDao.getAllGames()

    // Insert a new game
    suspend fun insert(game: Game): Boolean {
        return try {
            gameDao.insertGame(game) // This method should be a suspend function in GameDao
            true
        } catch (e: Exception) {
            false
        }
    }

}
