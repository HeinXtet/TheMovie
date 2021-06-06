package com.deevvdd.domain.model.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */

@Entity(tableName = "Movie")
@JsonClass(generateAdapter = true)
data class Movie(
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
    val movieType: String
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
}