package com.imdb.movies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    @SerializedName("id") val id: String? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("imageWidth") val imageWidth: Int? = null,
    @SerializedName("imageHeight") val imageHeight: Int? = null

) : Parcelable