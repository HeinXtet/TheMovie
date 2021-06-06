package com.deevvdd.domain.datasource

import com.deevvdd.domain.model.movie.CreditDetail
import com.deevvdd.domain.model.movie.Movie
import com.deevvdd.domain.model.movie.MovieDetails
import com.deevvdd.domain.type.Either
import com.deevvdd.domain.type.exception.DataException


/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */

interface RemoteDataSource {
    suspend fun getPopularMovies(page: Int): Either<DataException, List<Movie>>
    suspend fun getNowPlayingMovies(page: Int): Either<DataException, List<Movie>>
    suspend fun getUpcomingMovies(page: Int): Either<DataException, List<Movie>>

    suspend fun getMovieDetails(id: Int): Either<DataException, MovieDetails>

    suspend fun getCredits(id: Int): Either<DataException, CreditDetail>
}