package com.dicoding.moviecatalogue.core.domain.repository

import com.dicoding.moviecatalogue.core.domain.model.Movie
import com.dicoding.moviecatalogue.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IDataRepository {

    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getFavoritedMovies(): Flow<List<Movie>>

    fun setFavoritedMovie(movie: Movie, state: Boolean)
}