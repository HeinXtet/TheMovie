package com.deevvdd.tmdb.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.afollestad.materialdialogs.MaterialDialog
import com.deevvdd.tmdb.R
import com.deevvdd.tmdb.base.BaseFragment
import com.deevvdd.tmdb.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, HomeEvent>() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        movieAdapter = MovieAdapter()
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            adapter = movieAdapter
        }
        viewModel.movies.observe(
            viewLifecycleOwner,
            { movies ->
                movieAdapter.updateMovieList(movies)
            }
        )

        binding.spinnerMovieType.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newText ->
            when (newIndex) {
                0 -> {
                    updateMovies("now_playing")
                }
                1 -> {
                    updateMovies("popular")
                }
                2 -> {
                    updateMovies("upcoming")
                }
            }
        }
        return binding.root
    }

    private fun updateMovies(type: String) {
        movieAdapter.clearMovies()
        viewModel.updateMovieTypeAndFetchMovies(type)
    }

    override fun getOrCreateViewModel(): HomeViewModel = viewModel

    override fun renderEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ErrorFetchingMovie -> {
                MaterialDialog(requireContext()).show {
                    title(R.string.common_error_title)
                    message(text = event.errorMessage)
                    positiveButton(R.string.retry) {
                        it.dismiss()
                        viewModel.fetchNextMovies()
                    }
                    negativeButton(R.string.cancel) { it.dismiss() }
                }
            }
            else -> {
            }
        }
    }
}
