package com.deevvdd.data.network.mapper

import com.deevvdd.domain.model.movie.*
import com.deevvdd.domain.model.movie.response.CreditsData
import com.deevvdd.domain.model.movie.response.MovieDetailsData
import javax.inject.Inject
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
class MovieMapper @Inject constructor() {


    fun toDomain(movieData: MovieListData?, currentPage: Int, movieType: String): List<Movie> {
        return movieData?.results?.map {
            toDomain(
                it,
                movieData.totalPage ?: 0,
                currentPage,
                movieType
            )
        }
            .orEmpty()
    }

    private fun toDomain(
        data: MovieData,
        totalPage: Int,
        currentPage: Int,
        movieType: String
    ): Movie {
        return Movie(
            id = data.id ?: 0,
            title = data.originalTitle.orEmpty(),
            popularity = data.popularity?.toString().orEmpty(),
            releaseDate = data.release_date.orEmpty(),
            backdrop = data.backdrop_path.orEmpty(),
            totalPage = totalPage, overview = data.overview.orEmpty(),
            poster = data.poster_path.orEmpty(),
            page = currentPage,
            vote = data.vote_average,
            movieType = movieType
        )
    }


    fun toDomain(
        data: MovieDetailsData
    ): MovieDetails {
        return MovieDetails(
            id = data.id,
            title = data.original_title.orEmpty(),
            popularity = data.popularity.toString().orEmpty(),
            releaseDate = data.release_date.orEmpty(),
            backdrop = data.backdrop_path.orEmpty(),
            poster = data.poster_path.orEmpty(),
            vote = data.vote_average,
            page = 0,
            totalPage = 0,
            movieType = "MovieDetails",
            overview = data.overview.orEmpty(),
            productionCompany = data.production_companies.map {
                ProductionCompany(
                    it.name.orEmpty(),
                    it.logo_path.orEmpty(),
                    it.origin_country.orEmpty()
                )
            },
            genres = data.genres.orEmpty().map {
                Genre(
                    it.id,
                    it.name.orEmpty()
                )
            },
            duration = data.runtime ?: 0,
            tagLine = data.tagline.orEmpty()
        )
    }

    fun toDomain(data: CreditsData, movieId: Int): CreditDetail {
        return CreditDetail(
            id = data.id,
            cast = data.cast.map {
                Cast(
                    it.id,
                    it.gender,
                    it.known_for_department,
                    it.name,
                    it.original_name,
                    it.popularity,
                    it.profile_path,
                    it.cast_id,
                    it.character,
                    it.credit_id,
                    it.order
                )
            },
            movieId = movieId
        )
    }
}