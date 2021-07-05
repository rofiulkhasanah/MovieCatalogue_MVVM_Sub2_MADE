package com.dicoding.moviecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    var favorited: Boolean = false,
) : Parcelable {
    val baseURL: String get() = "https://image.tmdb.org/t/p/w500"
}