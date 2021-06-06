package com.deevvdd.data.network

import com.deevvdd.domain.model.movie.MovieListData
import com.deevvdd.domain.model.movie.response.CreditsData
import com.deevvdd.domain.model.movie.response.MovieDetailsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): Response<MovieListData>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): Response<MovieListData>


    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("page") page: Int
    ): Response<MovieListData>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") id: Int
    ): Response<MovieDetailsData>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int
    ) : Response<CreditsData>
}