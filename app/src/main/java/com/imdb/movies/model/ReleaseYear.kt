package com.imdb.movies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReleaseYear(

    @SerializedName("year") var year: String? = null,

) : Parcelable