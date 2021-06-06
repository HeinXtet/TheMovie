package com.deevvdd.domain.type.converter

import androidx.room.TypeConverter
import com.deevvdd.domain.model.movie.Cast
import com.deevvdd.domain.model.movie.Genre
import com.deevvdd.domain.model.movie.ProductionCompany
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by heinhtet deevvdd@gmail.com on 06,June,2021
 */

class Converters {

    @TypeConverter
    fun fromProductionCompanyList(value: List<ProductionCompany>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCompany>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toProductionCompanyList(value: String): List<ProductionCompany> {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCompany>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromGenreList(value: List<Genre>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGenreList(value: String): List<Genre> {
        val gson = Gson()
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromCastList(value: List<Cast>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Cast>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCastList(value: String): List<Cast> {
        val gson = Gson()
        val type = object : TypeToken<List<Cast>>() {}.type
        return gson.fromJson(value, type)
    }
}