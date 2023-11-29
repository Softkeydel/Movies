package com.imdb.movies.base

import android.app.Application


class AppClass : Application() {

    companion object {
       internal lateinit var instance: AppClass
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }


}