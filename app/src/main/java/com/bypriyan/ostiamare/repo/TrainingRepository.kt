package com.bypriyan.ostiamare.repo

import androidx.lifecycle.LiveData
import com.bypriyan.ostiamare.database.playerDB.TrainingDao
import com.bypriyan.ostiamare.model.Training
import javax.inject.Inject

class TrainingRepository @Inject constructor(private val trainingDao: TrainingDao) {

    fun getAllTrainingsList(): List<Training> {
        return trainingDao.getAllTrainingsList() // Query the DAO for list
    }

    val allTrainings: LiveData<List<Training>> = trainingDao.getAllTrainings()

    suspend fun insertTraining(training: Training) {
        trainingDao.insertTraining(training)
    }
}
