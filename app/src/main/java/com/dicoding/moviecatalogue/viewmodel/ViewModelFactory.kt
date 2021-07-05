package com.dicoding.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.moviecatalogue.core.domain.usecase.DataUseCase
import com.dicoding.moviecatalogue.ui.detail.movie.DetailMovieViewModel
import com.dicoding.moviecatalogue.ui.movie.MovieViewModel

class ViewModelFactory private constructor(private val dataUseCase: DataUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(dataUseCase) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                return DetailMovieViewModel(dataUseCase) as T
            }
//            modelClass.isAssignableFrom(com.dicoding.moviecatalogue.fav.FavoriteViewModel::class.java) -> {
//                return com.dicoding.moviecatalogue.fav.FavoriteViewModel(dataUseCase) as T
//            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}