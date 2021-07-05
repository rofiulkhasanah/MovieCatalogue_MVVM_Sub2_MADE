package com.dicoding.moviecatalogue.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.moviecatalogue.core.utils.DataTMDB
import com.dicoding.moviecatalogue.core.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val data = DataTMDB.generateMovies()[0]
    private val movieId = data.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: com.dicoding.moviecatalogue.core.data.DataRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(dataRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val expected =
            MutableLiveData<Resource<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>>()
        expected.value = Resource.success(data)

        `when`(dataRepository.getMovieById(movieId)).thenReturn(expected)
        viewModel.getMovie.observeForever(movieObserver)
        verify(movieObserver).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModel.getMovie.value
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun setMovieFavorite() {
        val expected =
            MutableLiveData<Resource<com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity>>()
        expected.value = Resource.success(data)
        val newState = !data.favorited

        `when`(dataRepository.getMovieById(movieId)).thenReturn(expected)
        viewModel.getMovie.observeForever(movieObserver)
        verify(movieObserver).onChanged(expected.value)
        dataRepository.getMovieById(movieId)
        viewModel.setFavoriteMovie()

        verify(dataRepository).setMovieFavorite(expected.value?.data as com.dicoding.moviecatalogue.core.data.source.local.entity.MovieEntity,
            newState)
        verifyNoMoreInteractions(movieObserver)

    }

}