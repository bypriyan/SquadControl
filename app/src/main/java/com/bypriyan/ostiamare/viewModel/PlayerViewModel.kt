package com.bypriyan.ostiamare.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypriyan.ostiamare.model.ModelPlayer
import com.bypriyan.ostiamare.repo.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val repository: PlayerRepository
) : ViewModel()  {

    private val _insertStatus = MutableLiveData<Boolean>()
    val insertStatus: LiveData<Boolean> get() = _insertStatus

    private val _playerInfo = MutableLiveData<ModelPlayer>()
    val playerInfo: LiveData<ModelPlayer> get() = _playerInfo

    val allPlayers: LiveData<List<ModelPlayer>> = repository.allPlayers

    fun insertPlayer(player: ModelPlayer) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val success = repository.insert(player)
                withContext(Dispatchers.Main){
                    _insertStatus.value = success  // Update the LiveData with the insertion result
                }
            }

        }
    }

    fun getPlayerById(playerId:Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val success = repository.getPlayerById(playerId)
                withContext(Dispatchers.Main){
                    _playerInfo.value = success // Update the LiveData with the insertion result
                }
            }

        }
    }

    // Add this update function
    fun updatePlayer(player: ModelPlayer) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val success = repository.updatePlayer(player)
                withContext(Dispatchers.Main) {
                    _insertStatus.value = success // Update the LiveData with the update result
                }
            }
        }
    }

}