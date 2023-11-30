package com.imdb.movies.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName(value="status", alternate=["response"])
    @Expose val status: Boolean = false,
    @Expose val message: String? = null,
    @SerializedName(value="data")
    @Expose val data: T? = null,
)
