package com.bypriyan.ostiamare.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "player_game_table",
    foreignKeys = [
        ForeignKey(entity = ModelPlayer::class, parentColumns = ["id"], childColumns = ["playerId"]),
        ForeignKey(entity = Game::class, parentColumns = ["id"], childColumns = ["gameId"])
    ],
    indices = [Index("playerId"), Index("gameId")]
)
data class PlayerGame(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val playerId: Int,  // Link to the player
    val gameId: Int,  // Link to the game
    val wasPresent: Boolean  // Whether the player was present (true) or absent (false)
)
