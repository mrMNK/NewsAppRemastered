package com.mrmnk.newsappremastered.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mrmnk.newsappremastered.databinding.ItemNewsInfoBinding
import com.mrmnk.newsappremastered.domain.NewsInfo
import com.squareup.picasso.Picasso

class NewsInfoAdapter(
    private val context: Context
) : ListAdapter<NewsInfo, NewsInfoViewHolder>(NewsInfoDiffCallback){

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
                Picasso.get().load(urlToImage).into(cardImageView)
                root.setOnClickListener {
                    onNewsClickListener?.onNewsClick(this)
                }
            }
        }
    }

    interface OnNewsClickListener {
        fun onNewsClick(newsInfo: NewsInfo)
    }

}