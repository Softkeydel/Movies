package com.imdb.movies.network

import com.imdb.movies.network.Status.ERROR
import com.imdb.movies.network.Status.LOADING
import com.imdb.movies.network.Status.SUCCESS
import com.imdb.movies.network.Status.UPDATE


data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> loading(data: T?): Resource<T> = Resource(status = LOADING, data = data, message = null)

        fun <T> success(data: T, message: String = "SUCCESS"): Resource<T> = Resource(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> = Resource(status = ERROR, data = data, message = message)

        fun <T> update(data: T?, message: String): Resource<T> = Resource(status = UPDATE, data = data, message = message)
    }
}