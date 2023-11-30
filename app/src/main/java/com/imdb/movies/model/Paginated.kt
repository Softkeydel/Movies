package com.imdb.movies.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Paginated<T> (
    @SerializedName("pageInfo")
    @Expose val pageInfo: PageInfo?,
    @SerializedName("list")
    @Expose val list: List<T>?,
)