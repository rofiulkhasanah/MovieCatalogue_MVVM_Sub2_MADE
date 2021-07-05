package com.dicoding.moviecatalogue.core.data.source.local

import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mCatalogueDao: com.dicoding.moviecatalogue.core.data.source.local.room.CatalogueDao) {

    fun getAllMovies(): Flow<List<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>> =
        mCatalogueDao.getMovies()

    fun getFavoritedMovies(): Flow<List<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>> =
        mCatalogueDao.getFavoritedMovie()

    suspend fun insertMovies(movies: List<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>) =
        mCatalogueDao.insertMovies(movies)

    fun setMoviesFavorite(
        movie: com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity,
        newState: Boolean,
    ) {
        movie.favorited = newState
        mCatalogueDao.updateMovie(movie)
    }
}