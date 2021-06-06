package com.deevvdd.data.network.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deevvdd.domain.model.movie.CreditDetail
import com.deevvdd.domain.model.movie.Movie
import com.deevvdd.domain.model.movie.MovieDetails
import com.deevvdd.domain.model.movie.response.CastData
import retrofit2.http.DELETE

/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM Movie WHERE page = :page_ and movieType = :movieType")
    suspend fun getMovies(page_: Int, movieType: String): List<Movie>

    @Query("SELECT * FROM Movie WHERE page <= :page_")
    suspend fun getAllMovies(page_: Int): List<Movie>

    @Query("DELETE from MOVIE WHERE movieType = :movieType")
    suspend fun deleteMovies(movieType: String)

    @Insert(entity = MovieDetails::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(details: MovieDetails)

    @Query("SELECT * FROM MovieDetails WHERE id= :movieId")
    suspend
    fun getMovieDetails(movieId: Int): MovieDetails?


    @Insert(entity = CreditDetail::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCredits(creditDetail: CreditDetail)

    @Query("SELECT * FROM CreditDetail WHERE movieId= :movieId")
    suspend fun getCredits(movieId: Int): CreditDetail?

}