package com.deevvdd.domain.repository

import com.deevvdd.domain.model.movie.CreditDetail
import com.deevvdd.domain.type.Either
import com.deevvdd.domain.model.movie.Movie
import com.deevvdd.domain.model.movie.MovieDetails
import com.deevvdd.domain.type.exception.DataException
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
interface MovieRepository {
    suspend fun fetchPopularMovies(page: Int): Either<DataException, List<Movie>>
    suspend fun fetchNowPlayingMovies(page: Int): Either<DataException, List<Movie>>
    suspend fun fetchUpcomingMovie(page: Int): Either<DataException, List<Movie>>


    suspend fun fetchMovieDetails(movieId: Int): Either<DataException, MovieDetails>

    suspend fun fetchCredits(movieId: Int): Either<DataException, CreditDetail>
}