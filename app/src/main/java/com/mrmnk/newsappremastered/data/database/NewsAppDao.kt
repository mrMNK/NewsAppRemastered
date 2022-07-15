package com.mrmnk.newsappremastered.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsAppDao {
    @Query("SELECT * FROM full_news_list ORDER BY publishedAt DESC")
    fun getNewsList(): LiveData<List<NewsAppDbModel>>

    @Query("SELECT * FROM full_news_list WHERE title == :title LIMIT 1")
    fun getNewsInfo(title: String): LiveData<NewsAppDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsList(newsList: List<NewsAppDbModel>)
}