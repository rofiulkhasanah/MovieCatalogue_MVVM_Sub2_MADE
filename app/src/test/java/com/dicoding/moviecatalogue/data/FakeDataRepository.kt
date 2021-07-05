package com.dicoding.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.moviecatalogue.core.data.source.local.entity.TvShowEntity
import com.dicoding.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.dicoding.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.dicoding.moviecatalogue.core.data.source.remote.response.TvShowResponse
import com.dicoding.moviecatalogue.core.utils.AppExecutors
import com.dicoding.moviecatalogue.core.vo.Resource

class FakeDataRepository constructor(
    private val remoteDataSource: com.dicoding.moviecatalogue.core.data.source.remote.RemoteDataSource,
    private val localDataSource: com.dicoding.moviecatalogue.core.data.source.local.LocalDataSource,
    private val appExecutors: AppExecutors,
) :
    com.dicoding.moviecatalogue.core.data.DataSource {

    override fun getAllMovies(): LiveData<Resource<PagedList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>>> {
        return object :
            com.dicoding.moviecatalogue.core.data.NetworkBoundResource<PagedList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>, List<MovieResponse>>(
                appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }


            override fun shouldFetch(data: PagedList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override fun saveCallResult(movieResponse: List<MovieResponse>) {
                val movieList =
                    ArrayList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>()
                for (response in movieResponse) {
                    val movie =
                        com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity(
                            response.id.toString(),
                            response.title,
                            response.overview,
                            response.poster_path,
                            response.release_date,
                            response.vote_average.toString(),
                            false
                        )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTvSHow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            com.dicoding.moviecatalogue.core.data.NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(
                appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShow()

            override fun saveCallResult(tvShowResponse: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowResponse) {
                    val tvShow = TvShowEntity(
                        response.id.toString(),
                        response.name,
                        response.overview,
                        response.posterPath,
                        response.voteAverage.toString(),
                        false
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getMovieById(movieId: String): LiveData<Resource<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>> {
        return object :
            com.dicoding.moviecatalogue.core.data.NetworkBoundResource<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity, List<MovieResponse>>(
                appExecutors) {
            override fun loadFromDB(): LiveData<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity> =
                localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override fun saveCallResult(movieResponse: List<MovieResponse>) {
                lateinit var movie: com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity
                for (response in movieResponse) {
                    if (response.id.toString() == movieId) {
                        movie =
                            com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity(
                                response.id.toString(),
                                response.title,
                                response.overview,
                                response.poster_path,
                                response.release_date,
                                response.vote_average.toString(),
                                false
                            )
                    }
                }
                localDataSource.insertMovie(movie)
            }
        }.asLiveData()
    }


    override fun getTvShowById(tvShowId: String): LiveData<Resource<TvShowEntity>> {
        return object :
            com.dicoding.moviecatalogue.core.data.NetworkBoundResource<TvShowEntity, List<TvShowResponse>>(
                appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShow()

            override fun saveCallResult(tvShowResponses: List<TvShowResponse>) {
                lateinit var tvShow: TvShowEntity
                for (response in tvShowResponses) {
                    if (response.id.toString() == tvShowId) {
                        tvShow = TvShowEntity(
                            response.id.toString(),
                            response.name,
                            response.overview,
                            response.posterPath,
                            response.voteAverage.toString(),
                            false
                        )
                    }
                }
                localDataSource.insertTvShow(tvShow)
            }
        }.asLiveData()
    }

    override fun setMovieFavorite(
        movie: com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity,
        state: Boolean,
    ) =
        appExecutors.diskIO().execute { localDataSource.setMoviesFavorite(movie, state) }

    override fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTvShowsFavorite(tvShow, state) }

    override fun getFavoritedTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedTvShows(), config).build()
    }

    override fun getFavoritedMovies(): LiveData<PagedList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedMovies(), config).build()
    }
}