package com.mrmnk.newsappremastered.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mrmnk.newsappremastered.R
import com.mrmnk.newsappremastered.databinding.NewsActivityBinding

class NewsActivity : AppCompatActivity() {

    private val component by lazy {
        (application as NewsApp).component
    }

    private val binding by lazy {
        NewsActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        launchNewsListFragment()
    }

    private fun launchNewsListFragment() {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsListFragment())
            .addToBackStack(null)
            .commit()
    }
}