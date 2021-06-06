package com.deevvdd.domain.datasource

import com.deevvdd.domain.model.movie.CreditDetail
import com.deevvdd.domain.model.movie.Movie
import com.deevvdd.domain.model.movie.MovieDetails
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
interface CacheDataSource {
    suspend fun getPopularMovies(page: Int): List<Movie>
    suspend fun getLatestMovies(page: Int): List<Movie>
    suspend fun getUpcomingMovies(page: Int): List<Movie>
    suspend fun insertMovies(movies: List<Movie>)

    suspend fun deleteMovies(movieType: String)

    suspend fun insertMovieDetails(details: MovieDetails)
    suspend fun getMovieDetail(movieId: Int): MovieDetails?

    suspend fun insertCredits(creditDetail: CreditDetail)

    suspend fun getCredits(movieId: Int): CreditDetail?
}