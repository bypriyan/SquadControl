package com.bypriyan.ostiamare.DataBase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypriyan.ostiamare.Class.Player
import kotlinx.coroutines.launch

class PlayerViewModel(private val repository: PlayerRepo) : ViewModel() {

    fun addPlayer(player: Player) {
        viewModelScope.launch {
            repository.addPlayer(player)
        }
    }

    fun fetchAllPlayers() {
        viewModelScope.launch {
            val players = repository.getAllPlayers()
            // Handle players (e.g., update LiveData)
        }
    }
}