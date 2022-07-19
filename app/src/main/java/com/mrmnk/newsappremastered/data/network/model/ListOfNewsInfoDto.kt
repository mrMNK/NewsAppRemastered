package com.mrmnk.newsappremastered.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListOfNewsInfoDto (
    @SerializedName("articles")
    @Expose
    val newsList: List<NewsInfoDto>? = null
)