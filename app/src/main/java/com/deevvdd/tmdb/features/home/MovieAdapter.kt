package com.deevvdd.tmdb.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deevvdd.domain.model.movie.Movie
import com.deevvdd.tmdb.databinding.ItemMovieBinding
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
class MovieAdapter : ListAdapter<Movie, RecyclerView.ViewHolder>(diffUtil) {

    fun updateMovieList(movies: List<Movie>) {
        submitList(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            holder.onBind(getItem(position))
        }
    }

    fun clearMovies() {
        notifyDataSetChanged()
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}
