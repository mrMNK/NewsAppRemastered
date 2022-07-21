package com.mrmnk.newsappremastered.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mrmnk.newsappremastered.data.database.NewsInfoDao
import com.mrmnk.newsappremastered.data.mapper.NewsMapper
import com.mrmnk.newsappremastered.data.network.ApiService
import com.mrmnk.newsappremastered.domain.NewsInfo
import com.mrmnk.newsappremastered.domain.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val mapper: NewsMapper,
    private val newsInfoDao: NewsInfoDao,
    private val apiService: ApiService
) : NewsRepository {

    override fun getNewsList(): LiveData<List<NewsInfo>> {
        return Transformations.map(newsInfoDao.getNewsList()) {
            mapper.mapListDbModelToListOfNewsInfo(it)
        }
    }

    override fun getNewsInfo(title: String): LiveData<NewsInfo> {
        return Transformations.map(newsInfoDao.getNewsInfo(title)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun getSortedNewsList(key: String): LiveData<List<NewsInfo>> {
        if (key == "") {
            getNewsList()
        }
        return Transformations.map(newsInfoDao.getSortedNewsList(key)) {
            mapper.mapListDbModelToListOfNewsInfo(it)
        }
    }

    override suspend fun loadData() {
        newsInfoDao.clearTable()
        try {
            val newsListDto = mapper.mapListDtoToListOfNewsDbModel(apiService.getNewsList())
            newsInfoDao.insertNewsList(newsListDto)
        } catch (e: Exception) {
        }
    }
}
