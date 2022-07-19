package com.mrmnk.newsappremastered.data.repository

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

    override fun getNewsList(): List<NewsInfo> {
        return mapper.mapListDbModelToListOfNewsInfo(newsInfoDao.getNewsList())
    }

    override fun getNewsInfo(title: String): NewsInfo {
        return mapper.mapDbModelToEntity(newsInfoDao.getNewsInfo(title))
    }

    override suspend fun loadData() {
        try {
            val newsListDto = mapper.mapListDtoToListOfNewsDbModel(apiService.getNewsList())
            newsInfoDao.insertNewsList(newsListDto)
        } catch (e: Exception) {
        }
    }
}