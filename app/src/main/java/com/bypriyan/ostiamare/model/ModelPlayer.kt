package com.bypriyan.ostiamare.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_table")
data class ModelPlayer(
    @PrimaryKey(true)
    val id:Int,
    val name:String,
    val surName:String,
    val DOB:String,
    val Role:String,
    val shirtNo:String,
    val notes:String
)
