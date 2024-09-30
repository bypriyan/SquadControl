package com.bypriyan.ostiamare.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "missed_training_table",
    foreignKeys = [
        ForeignKey(entity = ModelPlayer::class, parentColumns = ["id"], childColumns = ["playerId"]),
        ForeignKey(entity = Training::class, parentColumns = ["id"], childColumns = ["trainingId"])
    ],
    indices = [Index("playerId"), Index("trainingId")]
)
data class MissedTraining(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val playerId: Int,  // Foreign key linking to the player
    val trainingId: Int  // Foreign key linking to the training session
)
