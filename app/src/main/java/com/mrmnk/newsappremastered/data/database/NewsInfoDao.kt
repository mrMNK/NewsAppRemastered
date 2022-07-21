package com.mrmnk.newsappremastered.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsInfoDao {
    @Query("SELECT * FROM full_news_list ORDER BY publishedAt DESC")
    fun getNewsList(): LiveData<List<NewsInfoDbModel>>

    @Query("SELECT * FROM full_news_list WHERE title == :title LIMIT 1")
    fun getNewsInfo(title: String): LiveData<NewsInfoDbModel>

    @Query("SELECT * FROM full_news_list WHERE " +
            "title == :key or description == :key or content == :key")
    fun getSortedNewsList(key: String): LiveData<List<NewsInfoDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsList(newsList: List<NewsInfoDbModel>)

    @Query("DELETE FROM full_news_list")
    suspend fun clearTable()
}