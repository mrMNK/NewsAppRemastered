package com.mrmnk.newsappremastered.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrmnk.newsappremastered.domain.GetNewsInfoUseCase
import com.mrmnk.newsappremastered.domain.GetNewsListUseCase
import com.mrmnk.newsappremastered.domain.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase,
    private val getNewsInfoUseCase: GetNewsInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val newsList = getNewsListUseCase()

    fun getNewsInfo(title: String) = getNewsInfoUseCase(title)

    fun load() {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}