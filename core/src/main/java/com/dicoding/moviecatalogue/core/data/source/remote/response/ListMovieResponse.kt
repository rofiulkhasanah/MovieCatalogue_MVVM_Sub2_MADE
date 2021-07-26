package com.dicoding.moviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<com.dicoding.moviecatalogue.core.data.source.remote.response.MovieResponse>,
)
