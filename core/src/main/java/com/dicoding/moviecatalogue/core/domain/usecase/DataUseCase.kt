package com.dicoding.moviecatalogue.core.domain.usecase

import com.dicoding.moviecatalogue.core.domain.model.Movie
import com.dicoding.moviecatalogue.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface DataUseCase {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setFavoritedMovies(movie: Movie, state: Boolean)
}