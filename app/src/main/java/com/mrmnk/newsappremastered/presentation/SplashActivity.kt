package com.mrmnk.newsappremastered.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: NewsViewModel

    private val component by lazy {
        (application as NewsApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        viewModel.load()
        viewModel.newsList.observe(this) {
            if (it.isNotEmpty()) {
                launchNewsActivity()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    private fun launchNewsActivity() {
        val intent = Intent(this, NewsActivity::class.java)
        startActivity(intent)
    }
}