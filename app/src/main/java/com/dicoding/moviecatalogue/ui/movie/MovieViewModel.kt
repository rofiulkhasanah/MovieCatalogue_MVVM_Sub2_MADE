package com.dicoding.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.moviecatalogue.core.domain.usecase.DataUseCase

class MovieViewModel(dataUseCase: DataUseCase) : ViewModel() {
    val movie = dataUseCase.getAllMovies().asLiveData()
}