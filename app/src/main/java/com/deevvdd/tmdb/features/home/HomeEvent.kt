package com.deevvdd.tmdb.features.home
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
sealed class HomeEvent {
    data class Loading(val loading: Boolean) : HomeEvent()
    data class ErrorFetchingMovie(val errorMessage: String) : HomeEvent()
}
