package com.imdb.movies.view.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imdb.movies.database.AppDatabase
import com.imdb.movies.model.Movie
import com.imdb.movies.repository.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONObject


class DetailsViewModel(
    private val movieRepository: MovieRepository = MovieRepository.instance,
) : ViewModel() {





}