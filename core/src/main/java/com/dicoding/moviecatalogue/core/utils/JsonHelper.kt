package com.dicoding.moviecatalogue.core.utils

import android.content.Context
import com.dicoding.moviecatalogue.core.data.source.remote.response.MovieResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val setBuffer = ByteArray(`is`.available())
            `is`.read(setBuffer)
            `is`.close()
            String(setBuffer)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun loadMovie(): List<MovieResponse> {
        val movieList = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("id")
                val original_title = movie.getString("original_title")
                val overview = movie.getString("overview")
                val poster_path = movie.getString("poster_path")
                val release_date = movie.getString("release_date")
                val vote_average = movie.getString("vote_average")

                val movieResponse = MovieResponse(
                    id.toInt(),
                    original_title,
                    overview,
                    poster_path,
                    release_date,
                    vote_average.toDouble()
                )
                movieList.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return movieList
    }
}