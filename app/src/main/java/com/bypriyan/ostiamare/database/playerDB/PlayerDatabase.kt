package com.bypriyan.ostiamare.database.playerDB

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bypriyan.ostiamare.model.Game
import com.bypriyan.ostiamare.model.MissedTraining
import com.bypriyan.ostiamare.model.ModelPlayer
import com.bypriyan.ostiamare.model.PlayerGame
import com.bypriyan.ostiamare.model.Training
import com.bypriyan.ostiamare.util.Converters

@Database(
    entities = [ModelPlayer::class, Training::class, MissedTraining::class, Game::class, PlayerGame::class],
    version = 2,  // Increment the version
    exportSchema = false
)
@TypeConverters(Converters::class)  // Register your TypeConverters here
abstract class PlayerDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao
    abstract fun trainingDao(): TrainingDao
    abstract fun gameDao(): GameDao
    abstract fun playerGameDao(): PlayerGameDao

    companion object {
        @Volatile
        private var INSTANCE: PlayerDatabase? = null

        fun getDatabase(context: Context): PlayerDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlayerDatabase::class.java,
                    "player_database"
                )
                    // Add migration logic if needed
                    .fallbackToDestructiveMigration() // This will clear all data when the schema changes
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
