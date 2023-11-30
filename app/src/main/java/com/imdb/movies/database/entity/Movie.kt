package com.imdb.movies.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.imdb.movies.model.Movie
import kotlinx.android.parcel.Parcelize


@Parcelize
//@Entity(tableName = "movie")
@Entity(tableName = "movie", indices = [Index(value = ["movieId"], unique = true)])
data class Movie(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "movieId")
    var movieId: String = "",
    var title: String? = "",
    var description: String? = "",
    var image: String? = "",
    var rating: Float? = 0F,
    var voteCount: Int? = 0,
    var releaseYear: String? = "",
    var favourite: Boolean = false,
) : Parcelable {



    @Ignore
    constructor(movie: Movie) : this(0) {
        copy(movie)
    }


    private fun copy(movie: Movie) {
        this.movieId = movie.movieId ?: ""
        this.title = movie.title?.text
        this.description = movie.description
        this.image = movie.image?.imageUrl
        this.rating = movie.rating?.aggregateRating
        this.voteCount = movie.rating?.voteCount
        this.releaseYear = movie.releaseYear?.year
        this.favourite = movie.favourite
    }


}

