package com.bypriyan.ostiamare.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bypriyan.ostiamare.util.Converters

@Entity(tableName = "training_table")
data class Training(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,  // Date of the training session

    @TypeConverters(Converters::class)
    val absentPlayers: List<Int>  // List of absent player IDs
)