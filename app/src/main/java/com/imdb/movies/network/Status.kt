package com.imdb.movies.network

enum class Status(val value: Int) {
    SUCCESS(200),
    ERROR(400),
    LOADING(100),
    UPDATE(300);

//    override fun toString(): String {
//        return super.toString()
//    }
}