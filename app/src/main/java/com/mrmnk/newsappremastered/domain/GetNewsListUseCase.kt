package com.mrmnk.newsappremastered.domain

import javax.inject.Inject

class GetNewsListUseCase @Inject constructor (
    private val repository: NewsRepository
) {
    operator fun invoke() = repository.getNewsList()
}
