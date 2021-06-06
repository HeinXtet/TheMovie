package com.deevvdd.tmdb.di

import com.deevvdd.data.network.datasource.CacheDataSourceImpl
import com.deevvdd.data.network.datasource.LocalDataSourceImpl
import com.deevvdd.data.network.datasource.RemoteDataSourceImpl
import com.deevvdd.domain.datasource.CacheDataSource
import com.deevvdd.domain.datasource.LocalDataSource
import com.deevvdd.domain.datasource.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindMovieRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource

    @Binds
    abstract fun bindMovieLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource

    @Binds
    abstract fun bindMovieCacheDataSource(
        cacheDataSourceImpl: CacheDataSourceImpl
    ): CacheDataSource
}
