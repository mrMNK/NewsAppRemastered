package com.mrmnk.newsappremastered.di

import androidx.lifecycle.ViewModel
import com.mrmnk.newsappremastered.presentation.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel
}