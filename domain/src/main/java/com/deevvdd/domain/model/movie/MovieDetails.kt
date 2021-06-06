package com.deevvdd.domain.model.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by heinhtet deevvdd@gmail.com on 06,June,2021
 */

@Entity(tableName = "MovieDetails")
@JsonClass(generateAdapter = true)
data class MovieDetails(
    @PrimaryKey
    val id: Int,
    val page: Int,
    val totalPage: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val popularity: String,
    val vote: Float,
    val movieType: String,
    val productionCompany: List<ProductionCompany>,
    val genres: List<Genre>,
    val duration: Int,
    val tagLine: String
) {
    fun getPosterImageUrl(): String = "https://image.tmdb.org/t/p/w500${poster}"
    fun getBackdropImageUrl(): String = "https://image.tmdb.org/t/p/w500${backdrop}"

    fun getVoteRating() = (vote * 10).toInt()
    fun getVoteRatingStr() = vote.toString()

    fun getReadableFormatDate(): String {
        return try {
            val inputDateFormat = SimpleDateFormat("yyyy-MM-DD", Locale.ENGLISH)
            inputDateFormat.parse(releaseDate)
            val outputDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
            outputDateFormat.format(inputDateFormat.parse(releaseDate))
        } catch (e: Exception) {
            releaseDate
        }
    }

    fun getReadableDurations() = "($duration m)"
}

data class ProductionCompany(
    val name: String?,
    val logo: String?,
    val originCountry: String?
)

data class Genre(
    val id: Int,
    val name: String
)