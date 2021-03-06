package com.dicoding.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.moviecatalogue.core.data.source.local.entity.TvShowEntity
import com.dicoding.moviecatalogue.core.utils.DataTMDB
import com.dicoding.moviecatalogue.ui.movie.MovieViewModelTest
import com.dicoding.moviecatalogue.ui.tvshow.TvShowViewModelTest
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    private lateinit var viewModel: com.dicoding.moviecatalogue.fav.FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: com.dicoding.moviecatalogue.core.data.DataRepository

    @Mock
    private lateinit var observerMovie: Observer<PagedList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>>

    @Mock
    private lateinit var observerTvShow: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedListMovie: PagedList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>

    @Mock
    private lateinit var pagedListTvShow: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = com.dicoding.moviecatalogue.fav.FavoriteViewModel(dataRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val expected =
            MutableLiveData<PagedList<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>>()
        expected.value =
            MovieViewModelTest.PagedTestDataSourcesMovie.snapshot(DataTMDB.generateMovies())
        `when`(dataRepository.getFavoritedMovies()).thenReturn(expected)

        viewModel.getFavoriteMovies().observeForever(observerMovie)
        verify(observerMovie).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoriteMovies().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue.snapshot())
        assertEquals(expectedValue?.size, actualValue.size)
    }

    @Test
    fun getFavoriteTvShow() {
        val expected = MutableLiveData<PagedList<TvShowEntity>>()
        expected.value =
            TvShowViewModelTest.PagedTestDataSourcesTvShow.snapshot(DataTMDB.generateTvShow())
        `when`(dataRepository.getFavoritedTvShows()).thenReturn(expected)

        viewModel.getFavoriteTvShow().observeForever(observerTvShow)
        verify(observerTvShow).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoriteTvShow().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue.snapshot())
        assertEquals(expectedValue?.size, actualValue.size)
    }

}