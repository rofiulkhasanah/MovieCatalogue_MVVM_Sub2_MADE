package com.dicoding.moviecatalogue.core.data.source.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM MovieEntities")
    fun getMovies(): Flow<List<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>>

    @Query("SELECT * FROM MovieEntities WHERE favorited = 1")
    fun getFavoritedMovie(): Flow<List<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>)

    @Update
    fun updateMovie(movie: com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity)
}