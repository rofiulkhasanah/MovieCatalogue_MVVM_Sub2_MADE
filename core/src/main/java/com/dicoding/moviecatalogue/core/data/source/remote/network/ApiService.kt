package com.dicoding.moviecatalogue.core.data.source.remote.network

import com.dicoding.moviecatalogue.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val API_KEY = "07490aec0fbc640106a650a751a56636"
        const val PAGE = 1
    }

    @GET("movie/popular?api_key=${com.dicoding.moviecatalogue.core.data.source.remote.network.ApiService.Companion.API_KEY}&page=${com.dicoding.moviecatalogue.core.data.source.remote.network.ApiService.Companion.PAGE}")
    suspend fun getMoviePopular(): ListMovieResponse
}