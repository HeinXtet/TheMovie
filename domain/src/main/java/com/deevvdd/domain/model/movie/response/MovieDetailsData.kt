package com.deevvdd.domain.model.movie.response

import com.squareup.moshi.Json

/**
 * Created by heinhtet deevvdd@gmail.com on 06,June,2021
 */

data class MovieDetailsData(
    @field:Json(name = "backdrop_path")
    val backdrop_path: String?,

    @field:Json(name = "budget")
    val budget: Int,
    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "original_title")
    val original_title: String?,

    @field:Json(name = "overview")
    val overview: String?,

    @field:Json(name = "popularity")
    val popularity: Float,

    @field:Json(name = "poster_path")
    val poster_path: String?,

    @field:Json(name = "production_companies")
    val production_companies: List<ProductionCompanyData>,

    @field:Json(name = "release_date")
    val release_date: String?,

    @field:Json(name = "revenue")
    val revenue: Int,

    @field:Json(name = "runtime")
    val runtime: Int?,

    @field:Json(name = "status")
    val status: String?,

    @field:Json(name = "vote_count")
    val vote_count: Int,

    @field:Json(name = "vote_average")
    val vote_average: Float,

    @field:Json(name = "genres")
    val genres: List<GenreData>?,
    @field:Json(name = "tagline")
    val tagline: String?

)


data class ProductionCompanyData(
    @field:Json(name = "name")
    val name: String?,

    @field:Json(name = "logo_path")
    val logo_path: String?,

    @field:Json(name = "origin_country")
    val origin_country: String?
)

data class GenreData(
    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "name")
    val name: String?
)