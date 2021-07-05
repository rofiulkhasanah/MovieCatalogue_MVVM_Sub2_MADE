package com.dicoding.moviecatalogue.fav.di

import com.dicoding.moviecatalogue.fav.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favModule = module {
    viewModel { FavoriteViewModel(get()) }
}