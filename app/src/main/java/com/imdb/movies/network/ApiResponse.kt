package com.imdb.movies.network

import com.imdb.movies.network.Status.ERROR
import com.imdb.movies.network.Status.LOADING
import com.imdb.movies.network.Status.SUCCESS
import com.imdb.movies.network.Status.UPDATE


data class ApiResponse<out T>(val status: Status, val response: T?, val message: String?) {
    companion object {
        fun <T> loading(response: T?): ApiResponse<T> = ApiResponse(status = LOADING, response = response, message = null)

        fun <T> success(response: T, message: String = "SUCCESS"): ApiResponse<T> = ApiResponse(status = SUCCESS, response = response, message = null)

        fun <T> error(response: T?, message: String): ApiResponse<T> = ApiResponse(status = ERROR, response = response, message = message)

        fun <T> update(response: T?, message: String): ApiResponse<T> = ApiResponse(status = UPDATE, response = response, message = message)
    }
}