package com.mrmnk.newsappremastered.data.network

import com.mrmnk.newsappremastered.data.network.model.ListOfNewsInfoDto
import retrofit2.http.GET

interface ApiService {

    @GET("in.json")
    suspend fun getNewsList(): ListOfNewsInfoDto
}