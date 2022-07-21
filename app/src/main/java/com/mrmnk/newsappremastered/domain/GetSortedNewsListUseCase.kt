package com.mrmnk.newsappremastered.domain

import javax.inject.Inject

class GetSortedNewsListUseCase @Inject constructor (
    private val repository: NewsRepository
) {
    operator fun invoke(key: String) = repository.getSortedNewsList(key)
}