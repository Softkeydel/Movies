package com.imdb.movies.view.dashboard.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imdb.movies.database.AppDatabase
import com.imdb.movies.model.Movie
import com.imdb.movies.repository.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONObject


class FavouriteViewModel(
    private val movieRepository: MovieRepository = MovieRepository.instance,
) : ViewModel() {





    fun getFavouriteMovies() = movieRepository.getFavouriteMovies()


    fun addToFavourite(movie: Movie) {
        viewModelScope.launch {
            movieRepository.insertMovie(movie).collect{

            }
        }
    }

    fun removeFromFavourite(movie: Movie) {
        viewModelScope.launch {
            movieRepository.deleteMovie(movie).collect{

            }
        }
    }



}