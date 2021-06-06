package com.deevvdd.tmdb.features.detail

/**
 * Created by heinhtet deevvdd@gmail.com on 06,June,2021
 */
sealed class DetailEvent {
    data class ErrorFetchingMovie(val errorMessage: String) : DetailEvent()
    data class ErrorFetchingMovieCredit(val errorMessage: String) : DetailEvent()
}
