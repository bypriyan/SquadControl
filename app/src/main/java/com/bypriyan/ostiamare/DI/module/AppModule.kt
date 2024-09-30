package com.bypriyan.togocartstore.DI.module

import android.content.Context
import android.content.SharedPreferences
import android.location.Geocoder
import androidx.room.Room
import com.bypriyan.ostiamare.database.playerDB.GameDao
import com.bypriyan.ostiamare.database.playerDB.PlayerDao
import com.bypriyan.ostiamare.database.playerDB.PlayerDatabase
import com.bypriyan.ostiamare.database.playerDB.TrainingDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.Calendar
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PlayerDatabase {
        return Room.databaseBuilder(
            context,
            PlayerDatabase::class.java,
            "player_database"
        ).build()
    }

    @Singleton
    @Provides
    fun providePlayerDao(db: PlayerDatabase): PlayerDao {
        return db.playerDao()
    }

    @Singleton
    @Provides
    fun provideGameDao(db: PlayerDatabase): GameDao {
        return db.gameDao()
    }

    @Singleton
    @Provides
    fun trainingGameDao(db: PlayerDatabase): TrainingDao {
        return db.trainingDao()
    }
}