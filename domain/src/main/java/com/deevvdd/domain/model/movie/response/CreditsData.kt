package com.deevvdd.domain.model.movie.response

import com.squareup.moshi.Json

/**
 * Created by heinhtet deevvdd@gmail.com on 06,June,2021
 */

data class CreditsData(
    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "cast")
    val cast: List<CastData>
)

data class CastData(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "gender")
    val gender: Int?,
    @field:Json(name = "known_for_department")
    val known_for_department: String?,

    @field:Json(name = "name")
    val name: String?,

    @field:Json(name = "original_name")
    val original_name: String?,

    @field:Json(name = "popularity")
    val popularity: Float?,

    @field:Json(name = "profile_path")
    val profile_path: String?,


    @field:Json(name = "cast_id")
    val cast_id: Int?,

    @field:Json(name = "character")
    val character: String?,

    @field:Json(name = "credit_id")
    val credit_id: String?,

    @field:Json(name = "order")
    val order: Int?,

    )