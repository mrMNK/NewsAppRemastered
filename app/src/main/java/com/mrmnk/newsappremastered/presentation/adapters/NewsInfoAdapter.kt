package com.mrmnk.newsappremastered.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import com.mrmnk.newsappremastered.R
import com.mrmnk.newsappremastered.databinding.ItemNewsInfoBinding
import com.mrmnk.newsappremastered.domain.NewsInfo
import com.squareup.picasso.Picasso
import java.lang.Exception

class NewsInfoAdapter(
    private val context: Context
) : ListAdapter<NewsInfo, NewsInfoViewHolder>(NewsInfoDiffCallback) {

    var onNewsClickListener: OnNewsClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsInfoViewHolder {
        val binding = ItemNewsInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsInfoViewHolder, position: Int) {
        val news = getItem(position)
        with(holder.binding) {
            with(news) {
                cardTitleTextView.text = title
                cardDescriptionTextView.text = description
                cardPublishedAt.text = publishedAt
                loadImage(urlToImage, cardImageView)
                root.setOnClickListener {
                    onNewsClickListener?.onNewsClick(this)
                }
            }
        }
    }

    /**
     * Loads image from URL, if URL == null sets image from R.mipmap
     */
    private fun loadImage(url: String, imageView: ImageView) {
        try {
            Picasso.get().load(url).into(imageView)
        } catch (e: Exception) {
            Picasso.get().load(R.mipmap.ic_launcher).into(imageView)
        }
    }

    interface OnNewsClickListener {
        fun onNewsClick(newsInfo: NewsInfo)
    }
}