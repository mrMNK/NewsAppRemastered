package com.mrmnk.newsappremastered.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsAppDbModel::class], version = 1, exportSchema = false)
abstract class NewsAppDatabase : RoomDatabase() {
    companion object {
        private var db: NewsAppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): NewsAppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance: NewsAppDatabase =
                    Room.databaseBuilder(
                        context,
                        NewsAppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun newsAppDao(): NewsAppDao
}