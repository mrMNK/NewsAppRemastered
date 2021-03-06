package com.mrmnk.newsappremastered.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsInfoDbModel::class], version = 1, exportSchema = false)
abstract class NewsInfoDatabase : RoomDatabase() {
    companion object {
        private var db: NewsInfoDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): NewsInfoDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance: NewsInfoDatabase =
                    Room.databaseBuilder(
                        context,
                        NewsInfoDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun newsAppDao(): NewsInfoDao
}