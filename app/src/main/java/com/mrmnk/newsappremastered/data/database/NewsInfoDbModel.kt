package com.mrmnk.newsappremastered.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_news_list")
data class NewsInfoDbModel(
    @PrimaryKey
    val title: String,
    val description: String,
    val content: String,
    val urlToImage: String,
    val publishedAt: String
)