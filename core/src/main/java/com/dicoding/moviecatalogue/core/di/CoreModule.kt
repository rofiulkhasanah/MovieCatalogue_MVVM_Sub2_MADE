package com.dicoding.moviecatalogue.core.di

import androidx.room.Room
import com.dicoding.moviecatalogue.core.data.source.remote.network.ApiService
import com.dicoding.moviecatalogue.core.domain.repository.IDataRepository
import com.dicoding.moviecatalogue.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val BASE_URL = "https://api.themoviedb.org/3/"

val databaseModule = module {
    factory { get<com.dicoding.moviecatalogue.core.data.source.local.room.CatalogueDatabase>().catalogueDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            com.dicoding.moviecatalogue.core.data.source.local.room.CatalogueDatabase::class.java,
            "Catalogue.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { com.dicoding.moviecatalogue.core.data.source.local.LocalDataSource(get()) }
    single { com.dicoding.moviecatalogue.core.data.source.remote.RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IDataRepository> {
        com.dicoding.moviecatalogue.core.data.DataRepository(
            get(),
            get(),
            get()
        )
    }
}