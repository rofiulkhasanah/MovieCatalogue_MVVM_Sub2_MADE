package com.dicoding.moviecatalogue.fav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.moviecatalogue.core.domain.usecase.DataUseCase

class FavoriteViewModel(dataUseCase: DataUseCase) : ViewModel() {
    val favoriteMovie = dataUseCase.getFavoriteMovies().asLiveData()
}