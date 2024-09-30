package com.bypriyan.ostiamare.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypriyan.ostiamare.model.ModelPlayer
import com.bypriyan.ostiamare.model.PlayerAttendance
import com.bypriyan.ostiamare.model.Training
import com.bypriyan.ostiamare.repo.PlayerRepository
import com.bypriyan.ostiamare.repo.TrainingRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val trainingRepository: TrainingRepository,
    private val playerRepository: PlayerRepository
) : ViewModel() {

    val allPlayers: LiveData<List<ModelPlayer>> = playerRepository.allPlayers
    val allTrainings: LiveData<List<Training>> = trainingRepository.allTrainings

    // LiveData for attendance stats
    private val _attendanceStats = MutableLiveData<List<PlayerAttendance>>()
    val attendanceStats: LiveData<List<PlayerAttendance>> = _attendanceStats

    init {
        calculateAttendanceStats()
    }

    // Function to insert training session
    fun insertTraining(training: Training) {
        viewModelScope.launch {
            trainingRepository.insertTraining(training)
            calculateAttendanceStats()
        }
    }

    // Function to calculate attendance stats
    private fun calculateAttendanceStats() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val players = playerRepository.getAllPlayersList() // Fetch all players
                val trainings = trainingRepository.getAllTrainingsList() // Fetch all trainings

                val attendanceList = players.map { player ->
                    val absentCount = trainings.count { training -> player.id in training.absentPlayers }
                    val presentCount = trainings.size - absentCount
                    PlayerAttendance(player, presentCount, absentCount)
                }
                _attendanceStats.postValue(attendanceList)
            }

        }
    }
}
