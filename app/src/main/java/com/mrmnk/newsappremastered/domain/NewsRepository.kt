package com.mrmnk.newsappremastered.domain

import androidx.lifecycle.LiveData

interface NewsRepository {

    fun getNewsList(): LiveData<List<NewsInfo>>

    fun getNewsInfo(title: String): LiveData<List<NewsInfo>>

    suspend fun loadData()
}
