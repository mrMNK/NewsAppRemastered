package com.mrmnk.newsappremastered.data.mapper

import com.mrmnk.newsappremastered.data.database.NewsInfoDbModel
import com.mrmnk.newsappremastered.data.network.model.NewsInfoDto
import com.mrmnk.newsappremastered.domain.NewsInfo
import javax.inject.Inject

class NewsMapper @Inject constructor() {

    fun mapDtoToDbModel(dto: NewsInfoDto) = NewsInfoDbModel(
        title = dto.title ?: "",
        description = dto.description ?: "",
        content = dto.content ?: "",
        urlToImage = dto.urlToImage ?: "",
        publishedAt = dto.publishedAt ?: ""
    )

    fun mapListDtoToListOfNewsInfo(listDto: List<NewsInfoDto>): List<NewsInfoDbModel> {
        val result = mutableListOf<NewsInfoDbModel>()
        for (item in listDto) {
            val newsDbModelItem = mapDtoToDbModel(item)
            result.add(newsDbModelItem)
        }
        return result
    }

    fun mapDbModelToEntity(dbModel: NewsInfoDbModel) = NewsInfo (
        title = dbModel.title,
        description = dbModel.description,
        content = dbModel.content,
        urlToImage = dbModel.urlToImage,
        publishedAt = dbModel.publishedAt
    )
}