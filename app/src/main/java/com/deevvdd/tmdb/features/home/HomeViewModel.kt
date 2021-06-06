package com.deevvdd.tmdb.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.deevvdd.domain.model.movie.Movie
import com.deevvdd.domain.repository.MovieRepository
import com.deevvdd.domain.type.exception.CommonErrorMessage
import com.deevvdd.tmdb.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel<HomeEvent>() {

    val loading = MutableLiveData<Boolean>()
    private var _movies = MutableLiveData<List<Movie>>()
    private var movieType = "now_playing"

    val movies: LiveData<List<Movie>>
        get() {
            return _movies
        }

    private var currentPage = 1

    fun updateMovieTypeAndFetchMovies(type: String) {
        this.movieType = type
        currentPage = 1
        _movies.value = emptyList()
        fetchMovie()
    }

    init {
        if (currentPage == 1) {
            fetchMovie()
        }
    }

    fun <T> concatenate(vararg lists: List<T>): List<T> {
        return listOf(*lists).flatten()
    }

    private fun fetchMovie() {
        loading.value = true
        viewModelScope.launch {
            var apiCall = movieRepository.fetchNowPlayingMovies(currentPage)
            if (movieType == "popular") {
                apiCall = movieRepository.fetchPopularMovies(currentPage)
            } else if (movieType == "upcoming") {
                apiCall = movieRepository.fetchUpcomingMovie(currentPage)
            }
            apiCall.collect(
                {
                    loading.value = false
                    if (currentPage > 1) {
                        currentPage -= 1
                    }
                    emitEvent(
                        HomeEvent.ErrorFetchingMovie(
                            it.localizedMessage ?: CommonErrorMessage.UNKNOWN_ERROR_MESSAGE
                        )
                    )
                },
                {
                    loading.value = false
                    _movies.value = concatenate(_movies.value.orEmpty(), it)
                }
            )
        }
    }

    fun fetchNextMovies() {
        currentPage++
        fetchMovie()
    }
}
