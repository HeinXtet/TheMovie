package com.deevvdd.tmdb.di

import com.deevvdd.data.network.mapper.MovieMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
@Module
@InstallIn(SingletonComponent::class)
object MappersModule {
    @Provides
    fun provideAuthMapper() = MovieMapper()
}
