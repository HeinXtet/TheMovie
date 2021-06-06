package com.deevvdd.data.network.datasource

import com.deevvdd.data.network.persistance.MovieDao
import com.deevvdd.domain.datasource.CacheDataSource
import com.deevvdd.domain.model.movie.CreditDetail
import com.deevvdd.domain.model.movie.Movie
import com.deevvdd.domain.model.movie.MovieDetails
import javax.inject.Inject
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
class CacheDataSourceImpl @Inject constructor(private val movieDao: MovieDao) : CacheDataSource {
    override suspend fun getPopularMovies(page: Int) = movieDao.getMovies(page, "popular")
    override suspend fun getLatestMovies(page: Int): List<Movie> =
        movieDao.getMovies(page, "now_playing")

    override suspend fun getUpcomingMovies(page: Int): List<Movie> =
        movieDao.getMovies(page, "upcoming")

    override suspend fun insertMovies(movies: List<Movie>) = movieDao.insertMovies(movies)

    override suspend fun deleteMovies(movieType: String) = movieDao.deleteMovies(movieType)
    override suspend fun insertMovieDetails(details: MovieDetails) =
        movieDao.insertMovieDetails(details)

    override suspend fun getMovieDetail(movieId: Int) = movieDao.getMovieDetails(movieId)

    override suspend fun getCredits(movieId: Int) = movieDao.getCredits(movieId)
    override suspend fun insertCredits(creditDetail: CreditDetail) =
        movieDao.insertCredits(creditDetail)
}