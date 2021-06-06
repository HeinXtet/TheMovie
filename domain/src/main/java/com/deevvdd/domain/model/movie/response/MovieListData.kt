package com.deevvdd.domain.model.movie

import com.squareup.moshi.Json

/**
 * Created by heinhtet deevvdd@gmail.com on 06,June,2021
 */
data class MovieListData(
    @field:Json(name = "results")
    val results: List<MovieData>?,

    @field:Json(name = "total_pages")
    val totalPage: Int?
)


data class MovieData(
    @field:Json(name = "id")
    val id: Int?,

    @field:Json(name = "adult")
    val adult: Boolean?,

    @field:Json(name = "backdrop_path")
    val backdrop_path: String?,

    @field:Json(name = "poster_path")
    val poster_path: String?,

    @field:Json(name = "genre_ids")
    val genreIds: List<Int>?,

    @field:Json(name = "original_language")
    val originalLanguage: String?,


    @field:Json(name = "original_title")
    val originalTitle: String?,

    @field:Json(name = "overview")
    val overview: String?,


    @field:Json(name = "popularity")
    val popularity: Float?,


    @field:Json(name = "release_date")
    val release_date: String?,


    @field:Json(name = "vote_average")
    val vote_average: Float

)