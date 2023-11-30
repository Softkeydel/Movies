package com.imdb.movies.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PageInfo(
    @SerializedName("hasNextPage") val hasNextPage: Boolean? = null,
    @SerializedName("endCursor") val endCursor: String? = null,
    @SerializedName("total") val total: Int? = null
)