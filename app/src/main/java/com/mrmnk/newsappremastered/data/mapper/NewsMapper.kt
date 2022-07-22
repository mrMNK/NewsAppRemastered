package com.mrmnk.newsappremastered.data.mapper

import com.mrmnk.newsappremastered.data.database.NewsInfoDbModel
import com.mrmnk.newsappremastered.data.network.model.ListOfNewsInfoDto
import com.mrmnk.newsappremastered.data.network.model.NewsInfoDto
import com.mrmnk.newsappremastered.domain.NewsInfo
import javax.inject.Inject

class NewsMapper @Inject constructor() {

    private fun mapDtoToDbModel(dto: NewsInfoDto) = NewsInfoDbModel(
        title = dto.title ?: "",
        description = dto.description ?: "",
        content = dto.content ?: "",
        urlToImage = dto.urlToImage ?: "",
        publishedAt = parseDateString(dto.publishedAt)
    )

    fun mapListDtoToListOfNewsDbModel(listDto: ListOfNewsInfoDto): List<NewsInfoDbModel> {
        val result = mutableListOf<NewsInfoDbModel>()
        if (listDto.newsList == null) {
            return result
        }
        for (item in listDto.newsList) {
            val newsDbModelItem = mapDtoToDbModel(item)
            result.add(newsDbModelItem)
        }
        return result
    }

    fun mapDbModelToEntity(dbModel: NewsInfoDbModel) = NewsInfo(
        title = dbModel.title,
        description = dbModel.description,
        content = dbModel.content,
        urlToImage = dbModel.urlToImage,
        publishedAt = dbModel.publishedAt
    )

    fun mapListDbModelToListOfNewsInfo(listDbModel: List<NewsInfoDbModel>): List<NewsInfo> {
        val result = mutableListOf<NewsInfo>()
        for (item in listDbModel) {
            val newsInfo = mapDbModelToEntity(item)
            result.add(newsInfo)
        }
        return result
    }

    private fun parseDateString(date: String?): String {
        date?.let {
            return it.replace("T", " ").replace("Z", "")
        }
        return ""
    }
}