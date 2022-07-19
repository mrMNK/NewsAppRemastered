package com.mrmnk.newsappremastered.data.mapper

import com.mrmnk.newsappremastered.data.database.NewsAppDbModel
import com.mrmnk.newsappremastered.data.network.model.NewsInfoDto
import javax.inject.Inject

class NewsMapper @Inject constructor() {

    fun mapDtoToDbModel(dto: NewsInfoDto) = NewsAppDbModel(
        title = dto.title ?: "",
        description = dto.description ?: "",
        content = dto.content ?: "",
        urlToImage = dto.urlToImage ?: "",
        publishedAt = dto.publishedAt ?: ""
    )

}