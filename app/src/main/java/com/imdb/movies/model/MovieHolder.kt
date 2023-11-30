package com.imdb.movies.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieHolder(
    @SerializedName("title")
    @Expose val movie: Movie = Movie(),
    ) : BaseObservable(), Parcelable {

}
