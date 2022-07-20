package com.mrmnk.newsappremastered.di

import android.app.Application
import com.mrmnk.newsappremastered.presentation.NewsActivity
import com.mrmnk.newsappremastered.presentation.NewsApp
import com.mrmnk.newsappremastered.presentation.SplashActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: NewsApp)

    fun inject(activity: SplashActivity)

    fun inject(activity: NewsActivity)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}