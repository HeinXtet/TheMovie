package com.deevvdd.data.network.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.deevvdd.domain.model.movie.CreditDetail
import com.deevvdd.domain.model.movie.Movie
import com.deevvdd.domain.model.movie.MovieDetails
import com.deevvdd.domain.type.converter.Converters
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
@Database(
    entities = [
        Movie::class,
        MovieDetails::class,
        CreditDetail::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}
