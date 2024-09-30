package com.bypriyan.ostiamare.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypriyan.ostiamare.model.Game
import com.bypriyan.ostiamare.model.ModelPlayer
import com.bypriyan.ostiamare.repo.GameRepository
import com.bypriyan.ostiamare.repo.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel()  {

    private val _insertStatus = MutableLiveData<Boolean>()
    val insertStatus: LiveData<Boolean> get() = _insertStatus

    val allGames: LiveData<List<Game>> = repository.allGames

    fun insertGame(game: Game) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val success = repository.insert(game)
                withContext(Dispatchers.Main){
                    _insertStatus.value = success  // Update the LiveData with the insertion result
                }
            }

        }
    }

}