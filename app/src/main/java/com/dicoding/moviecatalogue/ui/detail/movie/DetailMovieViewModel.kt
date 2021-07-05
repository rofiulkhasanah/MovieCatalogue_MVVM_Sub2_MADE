package com.dicoding.moviecatalogue.ui.detail.movie

import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalogue.core.domain.model.Movie
import com.dicoding.moviecatalogue.core.domain.usecase.DataUseCase

class DetailMovieViewModel(private val dataUseCase: DataUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        dataUseCase.setFavoritedMovies(movie, newStatus)
}