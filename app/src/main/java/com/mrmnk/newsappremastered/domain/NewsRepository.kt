package com.mrmnk.newsappremastered.domain

interface NewsRepository {

    fun getNewsList(): List<NewsInfo>

    fun getNewsInfo(title: String): List<NewsInfo>

    suspend fun loadData()
}
