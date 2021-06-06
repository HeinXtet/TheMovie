package com.deevvdd.tmdb.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.afollestad.materialdialogs.MaterialDialog
import com.deevvdd.tmdb.R
import com.deevvdd.tmdb.base.BaseFragment
import com.deevvdd.tmdb.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by heinhtet deevvdd@gmail.com on 06,June,2021
 */
@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<DetailViewModel, DetailEvent>() {
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailBinding
    private val args: MovieDetailFragmentArgs by navArgs()
    private lateinit var castAdapter: CastAdapter

    override fun getOrCreateViewModel() = viewModel
    override fun renderEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.ErrorFetchingMovie -> {
                MaterialDialog(requireContext()).show {
                    title(R.string.common_error_title)
                    message(text = event.errorMessage)
                    positiveButton(R.string.retry) {
                        it.dismiss()
                        viewModel.getMovieDetails(args.movieId)
                    }
                }
            }
            is DetailEvent.ErrorFetchingMovieCredit -> {
                MaterialDialog(requireContext()).show {
                    title(R.string.common_error_title)
                    message(text = event.errorMessage)
                    positiveButton(R.string.retry) {
                        it.dismiss()
                        viewModel.getMovieCredits(args.movieId)
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        castAdapter = CastAdapter()

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            adapter = castAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieDetails(args.movieId)
        viewModel.getMovieCredits(args.movieId)

        viewModel.credits.observe(
            viewLifecycleOwner,
            { credits ->
                castAdapter.submitList(credits.cast)
            }
        )
    }
}
