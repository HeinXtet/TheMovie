package com.deevvdd.tmdb.features.home

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.deevvdd.domain.model.movie.Movie
import com.deevvdd.tmdb.databinding.ItemMovieBinding
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(movie: Movie) {
        binding.movie = movie
        binding.lyItemContainer.setOnClickListener {
            binding.root.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movieId = movie.id))
        }
    }
}
