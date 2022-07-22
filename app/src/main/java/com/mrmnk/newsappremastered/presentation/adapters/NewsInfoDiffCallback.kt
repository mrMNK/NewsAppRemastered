package com.mrmnk.newsappremastered.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.mrmnk.newsappremastered.domain.NewsInfo

object NewsInfoDiffCallback: DiffUtil.ItemCallback<NewsInfo>() {

    override fun areItemsTheSame(oldItem: NewsInfo, newItem: NewsInfo): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: NewsInfo, newItem: NewsInfo): Boolean {
        return oldItem == newItem
    }
}