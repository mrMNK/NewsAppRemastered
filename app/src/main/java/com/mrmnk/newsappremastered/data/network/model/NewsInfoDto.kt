package com.mrmnk.newsappremastered.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsInfoDto(
    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("description")
    @Expose
    val description: String? = null,

    @SerializedName("urlToImage")
    @Expose
    val urlToImage: String? = null,

    @SerializedName("publishedAt")
    @Expose
    val publishedAt: String? = null,

    @SerializedName("content")
    @Expose
    val content: String? = null
)