package com.imdb.movies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.imdb.movies.base.AppClass
import com.imdb.movies.database.dao.MovieDao
import com.imdb.movies.database.entity.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context = AppClass.instance) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "app_database"
            ).fallbackToDestructiveMigration()
                .addCallback(Callback())
                .build().also { instance = it }
        }
    }


    class Callback() : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
//            val dao = instance?.movieDao()


        }
    }

}