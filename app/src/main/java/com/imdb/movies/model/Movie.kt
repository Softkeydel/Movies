package com.imdb.movies.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize



@Parcelize
data class Movie(
    @SerializedName("id") var movieId: String? = null,
    @SerializedName("originalTitleText")  var title: Title? = Title(),
    @SerializedName("description")  var description: String? = null,
    @SerializedName("primaryImage") var image: Image? = Image(),
    @SerializedName("ratingsSummary") var rating: Rating? = Rating(),
    @SerializedName("releaseYear") var releaseYear: ReleaseYear? = ReleaseYear(),
    @Bindable @SerializedName("isFavourite") var favourite: Boolean = false,
    ) : BaseObservable(), Parcelable {





//    var _title: String? = null
//        get() = if (field.isNullOrEmpty()) title?.text else field


//    @Bindable
//    var _favourite: Boolean = false
//        set(value) {
//            field = value
//            notifyPropertyChanged(BR.favourite)
//        }


    fun updateFavourite(favourite: Boolean) {
        this.favourite = favourite
//        notifyPropertyChanged(BR.favourite)
    }




    override fun equals(other: Any?): Boolean {
        when (other) {
            is Movie -> {
                return this.movieId.equals(other.movieId)
                        && this.title?.text.equals(other.title?.text)
//                        && this.image?.imageUrl.equals(other.image?.imageUrl)
            }
            else -> return false
        }
    }

}
