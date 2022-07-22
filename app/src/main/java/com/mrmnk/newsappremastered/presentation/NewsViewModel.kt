package com.mrmnk.newsappremastered.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrmnk.newsappremastered.domain.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase,
    private val getNewsInfoUseCase: GetNewsInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase,
    private val getSortedNewsListUseCase: GetSortedNewsListUseCase
) : ViewModel() {

    val newsList = getNewsListUseCase()

    private var _sortedList: LiveData<List<NewsInfo>>? = null
    val sortedList: LiveData<List<NewsInfo>>
        get() = _sortedList ?: throw RuntimeException("_sortedList is null")

    fun getSortedList(key: String): LiveData<List<NewsInfo>> {
        _sortedList = getSortedNewsListUseCase(key)
        return getSortedNewsListUseCase(key)
    }

    fun getNewsInfo(title: String) = getNewsInfoUseCase(title)

    fun load() {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}