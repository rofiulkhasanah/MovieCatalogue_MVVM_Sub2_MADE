package com.dicoding.moviecatalogue.core.domain.usecase

import com.dicoding.moviecatalogue.core.domain.model.Movie
import com.dicoding.moviecatalogue.core.domain.repository.IDataRepository
import com.dicoding.moviecatalogue.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class DataInteractor(private val dataRepository: IDataRepository) : DataUseCase {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> = dataRepository.getAllMovies()

    override fun getFavoriteMovies(): Flow<List<Movie>> = dataRepository.getFavoritedMovies()

    override fun setFavoritedMovies(movie: Movie, state: Boolean) =
        dataRepository.setFavoritedMovie(movie, state)
}