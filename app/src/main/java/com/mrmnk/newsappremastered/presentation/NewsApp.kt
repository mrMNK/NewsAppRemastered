package com.mrmnk.newsappremastered.presentation

import android.app.Application
import com.mrmnk.newsappremastered.di.DaggerApplicationComponent

class NewsApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}