package com.mrmnk.newsappremastered.di

import android.app.Application
import com.mrmnk.newsappremastered.data.database.NewsInfoDao
import com.mrmnk.newsappremastered.data.database.NewsInfoDatabase
import com.mrmnk.newsappremastered.data.network.ApiFactory
import com.mrmnk.newsappremastered.data.network.ApiService
import com.mrmnk.newsappremastered.data.repository.NewsRepositoryImpl
import com.mrmnk.newsappremastered.domain.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideNewsInfoDao(
            application: Application
        ): NewsInfoDao {
            return NewsInfoDatabase.getInstance(application).newsAppDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}