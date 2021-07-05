package com.dicoding.moviecatalogue.fav

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviecatalogue.fav.databinding.ActivityFavoriteBinding
import com.dicoding.moviecatalogue.fav.di.favModule
import com.dicoding.moviecatalogue.ui.detail.movie.DetailMovieActivity
import com.dicoding.moviecatalogue.ui.movie.MovieAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favMovieViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favModule)

        supportActionBar?.hide()

        supportActionBar?.elevation = 0f

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }

        favMovieViewModel.favoriteMovie.observe(this, { movieFavData ->
            movieAdapter.setData(movieFavData)
            binding.viewEmpty.root.visibility =
                if (movieFavData.isNotEmpty()) View.GONE else View.VISIBLE
        })
        with(binding.rvFavoriteMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

    }
}