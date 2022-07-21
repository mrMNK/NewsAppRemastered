package com.mrmnk.newsappremastered.di

import android.app.Application
import com.mrmnk.newsappremastered.presentation.*
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

    fun inject(fragment: NewsListFragment)

    fun inject(fragment: NewsDetailFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}