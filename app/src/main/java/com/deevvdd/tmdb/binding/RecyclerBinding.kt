package com.deevvdd.tmdb.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deevvdd.tmdb.features.home.HomeViewModel
import com.deevvdd.tmdb.utils.PagingAdapter
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
object RecyclerBinding {
    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("paginationMovieList")
    fun paginationMoviesList(view: RecyclerView, viewModel: HomeViewModel) {
        PagingAdapter(
            recyclerView = view,
            isLoading = { viewModel.loading.value ?: false },
            loadMore = { viewModel.fetchNextMovies() },
            onLast = { false }
        ).run {
            threshold = 10
        }
    }
}
