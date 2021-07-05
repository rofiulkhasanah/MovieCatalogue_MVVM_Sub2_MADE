package com.dicoding.moviecatalogue.di

import com.dicoding.moviecatalogue.core.domain.usecase.DataInteractor
import com.dicoding.moviecatalogue.core.domain.usecase.DataUseCase
import com.dicoding.moviecatalogue.ui.detail.movie.DetailMovieViewModel
import com.dicoding.moviecatalogue.ui.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<DataUseCase> { DataInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}