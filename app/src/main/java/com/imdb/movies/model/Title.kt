package com.imdb.movies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Title (
    @SerializedName("text") var text : String? = null
) : Parcelable