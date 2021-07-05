package com.dicoding.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.Pager
import androidx.paging.PagingSource
import com.dicoding.moviecatalogue.core.utils.DataTMDB
import com.dicoding.moviecatalogue.core.vo.Resource
import com.nhaarman.mockitokotlin2.times
import kotlinx.coroutines.asCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private val local =
        Mockito.mock(com.dicoding.moviecatalogue.core.data.source.local.LocalDataSource::class.java)


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: com.dicoding.moviecatalogue.core.data.DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(dataRepository)
    }

    @Test
    fun getMovie() {
        val movies = PagedTestDataSourcesMovie.snapshot(DataTMDB.generateMovies())
        val expected =
            MutableLiveData<Resource<PagedList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>>>()
        expected.value = Resource.success(movies)
        `when`(dataRepository.getAllMovies()).thenReturn(expected)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getMovie().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue.data)
        assertEquals(expectedValue?.data?.size, actualValue.data.size)
    }

    @Test
    fun setMovieFavorite() {
        val dataTest = DataTMDB.generateMovies()[0]


        dataRepository.setMovieFavorite(dataTest, true)
        verify(local, times(1)).setMoviesFavorite(dataTest, true)
//
//        dataRepository.setMovieFavorite(dataTest, true)
//        com.nhaarman.mockitokotlin2.verify(local, Mockito.times(1)).setMoviesFavorite(dataTest, true)
    }


    class PagedTestDataSourcesMovie private constructor(private val items: List<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>) :
        PagingSource<Int, T>() {
        companion object {
            fun snapshot(items: List<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity> = listOf()): PagedList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity> {
                Executors.newSingleThreadExecutor()
                return Pager.flow(PagedTestDataSourcesMovie(items), 10)
                    .setNotifyDispatcher(fetchExecutor.asCoroutineDispatcher())
                    .setFetchDispatcher(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>,
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(
            params: LoadRangeParams,
            callback: LoadRangeCallback<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>,
        ) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}