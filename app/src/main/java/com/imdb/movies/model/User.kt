package com.imdb.movies.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @Expose var id: String = "",
    @Bindable @Expose var name: String = "",
    @SerializedName(value="user_id")
    @Expose var userId: String = "",
    @Expose var email: String = "",
    @SerializedName(value="mobile_no", alternate=["mobile"])
    @Expose var mobile: String = "",
) : BaseObservable(), Parcelable {


    fun update(user: User?): User {
        user?.let {
            this.id = it.id
            this.userId = it.userId
            this.name = it.name
            this.email = it.email
            this.mobile = it.mobile
        }
//        notifyPropertyChanged(BR._all)
        return this
    }
}
