package com.deevvdd.tmdb.binding

import android.annotation.SuppressLint
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.deevvdd.domain.model.movie.Genre
import com.deevvdd.domain.model.movie.ProductionCompany
import com.deevvdd.tmdb.R
/**
 * Created by heinhtet deevvdd@gmail.com on 06,June,2021
 */
object ViewBinding {

    @JvmStatic
    @BindingAdapter("ratingProgress")
    fun userRating(progressBar: ProgressBar, rating: Int) {
        when (rating) {
            in 80..100 -> {
                progressBar.progressDrawable =
                    ContextCompat.getDrawable(progressBar.context, R.drawable.circular_progress_bar)
            }
            in 50..79 -> {
                progressBar.progressDrawable =
                    ContextCompat.getDrawable(
                        progressBar.context,
                        R.drawable.circular_middle_progress_bar
                    )
            }
            else -> {
                progressBar.progressDrawable =
                    ContextCompat.getDrawable(
                        progressBar.context,
                        R.drawable.circular_low_progress_bar
                    )
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("genres", "durations")
    fun movieGenre(view: TextView, genres: List<Genre>?, durations: String?) {
        view.text = "${genres?.joinToString(", ") { it.name }} $durations"
    }

    @JvmStatic
    @BindingAdapter("productionCompanies")
    fun movieProductionCompanies(
        view: TextView,
        companies: List<ProductionCompany>?,
    ) {
        view.text = "${companies?.joinToString(", ") { it.name.orEmpty() }}"
    }
}
