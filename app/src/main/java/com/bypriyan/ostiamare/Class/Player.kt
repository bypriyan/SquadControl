package com.bypriyan.ostiamare.Class
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "players")
data class Player(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // Unique ID for each player
    val name: String,
    val age: Int,
    val address: String,
    val image: String, // Store the image path or URI
    val phoneNumber: String,
    val email: String,
    val role: String // Role like
)
