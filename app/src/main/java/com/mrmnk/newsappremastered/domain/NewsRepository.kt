package com.mrmnk.newsappremastered.domain

interface NewsRepository {

    fun getNewsList(): List<NewsInfo>

    fun getNewsInfo(title: String): NewsInfo

    suspend fun loadData()
}
