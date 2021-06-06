package com.deevvdd.domain.model.movie

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by heinhtet deevvdd@gmail.com on 06,June,2021
 */

@Entity(tableName = "CreditDetail")
data class CreditDetail(
    @PrimaryKey
    val movieId: Int,
    val id: Int,
    val cast: List<Cast>
)

data class Cast(
    val id: Int,
    val gender: Int?,
    val known_for_department: String?,
    val name: String?,
    val originalName: String?,
    val popularity: Float?,
    val profilePath: String?,
    val castId: Int?,
    val character: String?,
    val creditId: String?,
    val order: Int?,
) {
    fun getProfileImage() = "https://image.tmdb.org/t/p/w500/${profilePath}"
}