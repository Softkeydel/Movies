package com.imdb.movies.view.dashboard.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imdb.movies.model.Movie
import com.imdb.movies.repository.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONObject


class HomeViewModel(
    private val movieRepository: MovieRepository = MovieRepository.instance,
) : ViewModel() {




    fun getMovies(params: JSONObject?) = movieRepository.getMoviesFromServer(params)



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