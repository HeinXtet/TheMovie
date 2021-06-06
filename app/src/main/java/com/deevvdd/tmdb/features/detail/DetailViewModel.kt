package com.deevvdd.tmdb.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.deevvdd.domain.model.movie.CreditDetail
import com.deevvdd.domain.model.movie.MovieDetails
import com.deevvdd.domain.repository.MovieRepository
import com.deevvdd.domain.type.exception.CommonErrorMessage
import com.deevvdd.tmdb.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by heinhtet deevvdd@gmail.com on 06,June,2021
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : BaseViewModel<DetailEvent>() {

    private val _movie = MutableLiveData<MovieDetails>()
    private val _loading = MutableLiveData(false)
    private val _credits = MutableLiveData<CreditDetail>()

    val movie: LiveData<MovieDetails>
        get() {
            return _movie
        }

    val loading: LiveData<Boolean>
        get() {
            return _loading
        }

    val credits: LiveData<CreditDetail>
        get() {
            return _credits
        }

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            _loading.value = true
            repository.fetchMovieDetails(id).collect(
                {
                    _loading.value = false
                    emitEvent(
                        DetailEvent.ErrorFetchingMovie(
                            it.localizedMessage ?: CommonErrorMessage.UNKNOWN_ERROR_MESSAGE
                        )
                    )
                },
                {
                    _loading.value = false
                    _movie.value = it
                }
            )
        }
    }

    fun getMovieCredits(id: Int) {
        viewModelScope.launch {
            _loading.value = true
            repository.fetchCredits(id).collect(
                {
                    _loading.value = false
                    emitEvent(
                        DetailEvent.ErrorFetchingMovieCredit(
                            it.localizedMessage ?: CommonErrorMessage.UNKNOWN_ERROR_MESSAGE
                        )
                    )
                },
                {
                    _loading.value = false
                    _credits.value = it
                }
            )
        }
    }
}
