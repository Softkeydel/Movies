package com.imdb.movies.repository

import com.imdb.movies.model.BaseResponse
import com.imdb.movies.model.Movie
import com.imdb.movies.model.Paginated
import com.imdb.movies.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject

interface IMovieRepository {

    fun getMovies(params: JSONObject?): Flow<ApiResponse<BaseResponse<Paginated<Movie>>>>

    fun getMoviesFromServer(params: JSONObject?): Flow<ApiResponse<BaseResponse<Paginated<Movie>>>>

    fun getFavouriteMovies(): Flow<ApiResponse<List<Movie>?>>

    fun insertMovie(movie: Movie): Flow<ApiResponse<*>>

    fun deleteMovie(movie: Movie): Flow<ApiResponse<Any>>
}