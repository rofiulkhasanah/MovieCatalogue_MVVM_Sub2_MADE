package com.dicoding.moviecatalogue.core.utils

import com.dicoding.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.dicoding.moviecatalogue.core.domain.model.Movie

object DataMapper {
    fun mapMovieResponsestoEntities(input: List<MovieResponse>): List<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity> {
        val movieList =
            ArrayList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>()
        input.map {
            val movie = com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity(
                id = it.id,
                overview = it.overview,
                poster_path = it.poster_path,
                release_date = it.release_date,
                title = it.title,
                vote_average = it.vote_average,
                favorited = false
            )
            movieList.add(movie)
        }
        return movieList
    }


    fun mapMovieEntitiesToDomain(input: List<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                poster_path = it.poster_path,
                release_date = it.release_date,
                title = it.title,
                vote_average = it.vote_average,
                favorited = it.favorited
            )
        }

    fun mapDomainToMovieEntity(input: Movie) =
        com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity(
            id = input.id,
            overview = input.overview,
            poster_path = input.poster_path,
            release_date = input.release_date,
            vote_average = input.vote_average,
            title = input.title,
            favorited = input.favorited
        )

}