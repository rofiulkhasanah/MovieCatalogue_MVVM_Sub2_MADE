package com.dicoding.moviecatalogue.core.data

import com.dicoding.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.dicoding.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.dicoding.moviecatalogue.core.domain.model.Movie
import com.dicoding.moviecatalogue.core.domain.repository.IDataRepository
import com.dicoding.moviecatalogue.core.utils.AppExecutors
import com.dicoding.moviecatalogue.core.utils.DataMapper
import com.dicoding.moviecatalogue.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataRepository(
    private val remoteDataSource: com.dicoding.moviecatalogue.core.data.source.remote.RemoteDataSource,
    private val localDataSource: com.dicoding.moviecatalogue.core.data.source.local.LocalDataSource,
    private val appExecutors: AppExecutors,
) :
    IDataRepository {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object :
            com.dicoding.moviecatalogue.core.data.NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies()
                    .map { DataMapper.mapMovieEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsestoEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getFavoritedMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoritedMovies().map { DataMapper.mapMovieEntitiesToDomain(it) }
    }

    override fun setFavoritedMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToMovieEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMoviesFavorite(movieEntity, state) }
    }
}