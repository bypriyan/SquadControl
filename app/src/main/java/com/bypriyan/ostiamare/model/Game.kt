package com.bypriyan.ostiamare.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class Game(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,  // Game date in "yyyy-MM-dd" format
    val opponent: String,  // Opponent team
    val result: String  // Result of the game, e.g., "win", "lose", "draw"
)