package com.dicoding.moviecatalogue.ui.detail.movie

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.moviecatalogue.R
import com.dicoding.moviecatalogue.core.domain.model.Movie
import com.dicoding.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.dicoding.moviecatalogue.databinding.MovieDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var detailBinding: MovieDetailBinding
    private lateinit var binding: ActivityDetailMovieBinding

    private val detailViewModel: DetailMovieViewModel by viewModel()
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        detailBinding = binding.detailContent

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            supportActionBar?.title = detailMovie.title
            detailBinding.apply {
                tvMovieDescription.text = detailMovie.overview
                tvMovieDate.text = detailMovie.release_date
                tvMovieTitle.text = detailMovie.title
                tvMovieRating.text = resources.getString(R.string.rating, detailMovie.vote_average.toString())
                Glide.with(this@DetailMovieActivity)
                    .load(detailMovie.baseURL.plus(detailMovie.poster_path))
                    .into(imgMovieImage)
            }
            var statusFavorite = detailMovie.favorited
            setFavoriteState(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setFavoriteState(statusFavorite)
            }


        }
    }

    private fun setFavoriteState(state: Boolean) {
        if (state) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }
}