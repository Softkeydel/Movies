package com.imdb.movies.repository


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imdb.movies.base.AppClass
import com.imdb.movies.database.AppDatabase

import com.imdb.movies.model.BaseResponse
import com.imdb.movies.model.Movie
import com.imdb.movies.model.MovieHolder
import com.imdb.movies.model.Paginated
import com.imdb.movies.network.ApiClient
import com.imdb.movies.network.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

class MovieRepository private constructor(): IMovieRepository {


    companion object {
        private val apiService = ApiClient.apiService
        private val appDatabase = AppDatabase.getInstance()
        val instance: MovieRepository by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            MovieRepository()
        }
    }








    override fun getMoviesFromServer(params: JSONObject?) = flow {
//        emit(ApiResponse.loading(null))

        emit(
            try {
                val response = apiService.getMovies(JSONObject())
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.status ?: false) {

                        ApiResponse.success(response = BaseResponse(
                            status = true,
                            message = responseBody?.message,
                            data = Paginated(
                                pageInfo = responseBody?.data?.pageInfo,
                                list = responseBody?.data?.list?.map { it.movie }
                            )
                        ))

//                        ApiResponse.success(response = response.body())
                    } else {
                        ApiResponse.error(response = null, message = response.body()?.message ?: "")
                    }
                } else {


                    ApiResponse.error(
                        response = null,
                        message = response.headers().get("msg") ?: "Some Error Occurred"
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                ApiResponse.error(response = null, message = e.message ?: "Failed to connect server!")
            }
        )
    }



    override fun getFavouriteMovies() = flow {
//        emit(ApiResponse.loading(null))

        appDatabase.movieDao().getFavouriteMovies().map { it.map { movie ->
            Movie(movie) } }.collect{
            emit(ApiResponse.success(it))
        }
    }


    override fun insertMovie(movie: Movie) = flow {
        emit(ApiResponse.loading(null))

        appDatabase.movieDao().insert(com.imdb.movies.database.entity.Movie(movie))

        emit(ApiResponse.loading(movie))
    }


    override fun deleteMovie(movie: Movie) = flow {
        emit(ApiResponse.loading(null))

        appDatabase.movieDao().deleteById(movie.movieId)

        emit(ApiResponse.loading(movie))
    }



}