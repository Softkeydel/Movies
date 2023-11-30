package com.imdb.movies.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.imdb.movies.database.entity.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {


        @Query("SELECT * FROM movie")
        fun getMovies(): Flow<List<Movie>>

//        @Query("SELECT * FROM movie where favourite = 1 AND movieId in (SELECT DISTINCT movieId FROM movie)")
        @Query("SELECT * FROM movie WHERE favourite = 1")
        fun getFavouriteMovies(): Flow<List<Movie>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(movie: Movie)


        @Query("DELETE FROM movie WHERE movieId = :movieId")
        suspend fun deleteById(movieId: String?)



}