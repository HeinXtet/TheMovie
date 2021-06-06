package com.deevvdd.tmdb.di

import android.content.Context
import androidx.room.Room
import com.deevvdd.data.network.persistance.AppDatabase
import com.deevvdd.data.network.persistance.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by heinhtet  deevvdd@gmail.comon 05,June,2021
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext application: Context,
    ): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "TMDB_Movies.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }
}
