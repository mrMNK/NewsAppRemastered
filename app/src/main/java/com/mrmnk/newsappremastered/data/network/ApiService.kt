package com.mrmnk.newsappremastered.data.network

import com.mrmnk.newsappremastered.data.network.model.ListOfNewsInfo
import retrofit2.http.GET

interface ApiService {

    @GET("")
    suspend fun getNewsList(): ListOfNewsInfo
}