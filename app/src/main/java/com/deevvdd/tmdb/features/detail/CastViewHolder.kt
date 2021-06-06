package com.deevvdd.tmdb.features.detail

import androidx.recyclerview.widget.RecyclerView
import com.deevvdd.domain.model.movie.Cast
import com.deevvdd.tmdb.databinding.ItemCastBinding
/**
 * Created by heinhtet deevvdd@gmail.com on 06,June,2021
 */
class CastViewHolder(val binding: ItemCastBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(cast: Cast) {
        binding.cast = cast
    }
}
