package com.deevvdd.tmdb

import android.content.Context
import androidx.lifecycle.ViewModel
import com.deevvdd.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository,
    @ApplicationContext val context: Context
) : ViewModel()
