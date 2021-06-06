package com.deevvdd.data.network.datasource

import com.deevvdd.domain.type.Either
import com.deevvdd.data.network.ApiService
import com.deevvdd.data.network.mapper.MovieMapper
import com.deevvdd.domain.datasource.RemoteDataSource
import com.deevvdd.domain.model.movie.CreditDetail
import com.deevvdd.domain.model.movie.Movie
import com.deevvdd.domain.model.movie.MovieDetails
import com.deevvdd.domain.service.BaseRemoteRepository
import com.deevvdd.domain.type.exception.DataException
import javax.inject.Inject
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieMapper: MovieMapper
) :
    RemoteDataSource, BaseRemoteRepository() {
    override suspend fun getPopularMovies(page: Int): Either<DataException, List<Movie>> {
        return safeApiCall(
            apiCall = { apiService.getPopularMovies(page) },
            mapper = {
                movieMapper.toDomain(movieData = it, currentPage = page, movieType = "popular")
            })
    }

    override suspend fun getNowPlayingMovies(page: Int): Either<DataException, List<Movie>> {
        return safeApiCall(
            apiCall = { apiService.getNowPlayingMovies(page) },
            mapper = {
                movieMapper.toDomain(movieData = it, currentPage = page, movieType = "now_playing")
            })
    }

    override suspend fun getUpcomingMovies(page: Int): Either<DataException, List<Movie>> {
        return safeApiCall(
            apiCall = { apiService.getUpComingMovies(page) },
            mapper = {
                movieMapper.toDomain(movieData = it, currentPage = page, movieType = "upcoming")
            })
    }


    override suspend fun getMovieDetails(id: Int): Either<DataException, MovieDetails> {
        return safeApiCall(
            apiCall = { apiService.getMovieDetails(id) },
            mapper = {
                movieMapper.toDomain(it)
            })
    }

    override suspend fun getCredits(id: Int): Either<DataException, CreditDetail> {
        return safeApiCall(
            apiCall = { apiService.getMovieCredits(id) },
            mapper = {
                movieMapper.toDomain(it, id)
            })
    }
}