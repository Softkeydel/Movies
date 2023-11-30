package com.imdb.movies.network


import com.imdb.movies.model.*
import com.imdb.movies.network.ApiConstant.GET_MOVIES
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url


interface ApiService {

    @POST(GET_MOVIES)
    suspend fun getMovies(@Body param: JSONObject?): Response<BaseResponse<Paginated<MovieHolder>>>


}