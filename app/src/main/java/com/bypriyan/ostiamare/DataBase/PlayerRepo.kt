package com.bypriyan.ostiamare.DataBase

import com.bypriyan.ostiamare.Class.Player

class PlayerRepo(private val playerDao: PlayerDao) {
    suspend fun addPlayer(player: Player) {
        playerDao.insert(player)
    }

    suspend fun getAllPlayers(): List<Player> {
        return playerDao.getAllPlayers()
    }
}