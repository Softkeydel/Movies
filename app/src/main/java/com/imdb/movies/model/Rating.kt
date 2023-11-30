package com.imdb.movies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(

    @SerializedName("aggregateRating") val aggregateRating: Float? = 0F,
    @SerializedName("voteCount") val voteCount: Int? = 0,

) : Parcelable