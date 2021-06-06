package com.deevvdd.tmdb.di

import com.deevvdd.data.network.respository.MovieRepositoryImpl
import com.deevvdd.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}
