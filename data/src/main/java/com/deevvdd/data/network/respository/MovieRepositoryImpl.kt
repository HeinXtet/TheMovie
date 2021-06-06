package com.deevvdd.data.network.respository

import com.deevvdd.domain.datasource.CacheDataSource
import com.deevvdd.domain.datasource.RemoteDataSource
import com.deevvdd.domain.model.movie.CreditDetail
import com.deevvdd.domain.model.movie.Movie
import com.deevvdd.domain.model.movie.MovieDetails
import com.deevvdd.domain.repository.MovieRepository
import com.deevvdd.domain.service.BaseRemoteRepository
import com.deevvdd.domain.type.Either
import com.deevvdd.domain.type.exception.DataException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val cacheDataSource: CacheDataSource
) :
    MovieRepository, BaseRemoteRepository() {
    override suspend fun fetchPopularMovies(page: Int): Either<DataException, List<Movie>> {
        val movies = cacheDataSource.getPopularMovies(page)
        return if (movies.isNotEmpty()) {
            Either.Right(movies)
        } else {
            remoteDataSource.getPopularMovies(page).collect({
                Either.Left(it)
            }, {
                GlobalScope.launch { cacheDataSource.insertMovies(it) }
                Either.Right(it)
            })
        }
    }


    override suspend fun fetchNowPlayingMovies(page: Int): Either<DataException, List<Movie>> {
        val movies = cacheDataSource.getLatestMovies(page)
        return if (movies.isNotEmpty()) {
            Either.Right(movies)
        } else {
            remoteDataSource.getNowPlayingMovies(page).collect({
                Either.Left(it)
            }, {
                GlobalScope.launch { cacheDataSource.insertMovies(it) }
                Either.Right(it)
            })
        }
    }

    override suspend fun fetchUpcomingMovie(page: Int): Either<DataException, List<Movie>> {
        val movies = cacheDataSource.getUpcomingMovies(page)
        return if (movies.isNotEmpty()) {
            Either.Right(movies)
        } else {
            remoteDataSource.getUpcomingMovies(page).collect({
                Either.Left(it)
            }, {
                GlobalScope.launch { cacheDataSource.insertMovies(it) }
                Either.Right(it)
            })
        }
    }

    override suspend fun fetchMovieDetails(movieId: Int): Either<DataException, MovieDetails> {
        val movieDetails = cacheDataSource.getMovieDetail(movieId)
        return if (movieDetails != null) {
            Either.Right(movieDetails)
        } else {
            remoteDataSource.getMovieDetails(movieId).collect({
                Either.Left(it)
            }, {
                GlobalScope.launch { cacheDataSource.insertMovieDetails(it) }
                Either.Right(it)
            })
        }
    }

    override suspend fun fetchCredits(movieId: Int): Either<DataException, CreditDetail> {
        val credits = cacheDataSource.getCredits(movieId)
        return if (credits != null) {
            Either.Right(credits)
        } else {
            remoteDataSource.getCredits(movieId).collect({
                Either.Left(it)
            }, {
                GlobalScope.launch { cacheDataSource.insertCredits(it) }
                Either.Right(it)
            })
        }
    }
}