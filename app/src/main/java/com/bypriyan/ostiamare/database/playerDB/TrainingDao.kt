package com.bypriyan.ostiamare.database.playerDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bypriyan.ostiamare.model.MissedTraining
import com.bypriyan.ostiamare.model.Training

@Dao
interface TrainingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTraining(training: Training)

    @Query("SELECT * FROM training_table")
    fun getAllTrainings(): LiveData<List<Training>>

    @Query("SELECT * FROM training_table")
    fun getAllTrainingsList(): List<Training> // Synchronous list query
}
