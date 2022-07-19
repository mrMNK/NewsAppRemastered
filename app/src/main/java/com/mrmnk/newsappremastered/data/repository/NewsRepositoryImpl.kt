package com.mrmnk.newsappremastered.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.mrmnk.newsappremastered.data.database.NewsInfoDao
import com.mrmnk.newsappremastered.data.mapper.NewsMapper
import com.mrmnk.newsappremastered.data.network.ApiService
import com.mrmnk.newsappremastered.domain.NewsInfo
import com.mrmnk.newsappremastered.domain.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val mapper: NewsMapper,
    private val newsAppDao: NewsInfoDao,
    private val application: Application,
    private val apiService: ApiService
): NewsRepository {

    override fun getNewsList(): LiveData<List<NewsInfo>> {
        TODO("Not yet implemented")
    }

    override fun getNewsInfo(title: String): LiveData<List<NewsInfo>> {
        TODO("Not yet implemented")
    }

    override suspend fun loadData() {
        val listOfNewsInfo = apiService.getNewsList()

    }
}