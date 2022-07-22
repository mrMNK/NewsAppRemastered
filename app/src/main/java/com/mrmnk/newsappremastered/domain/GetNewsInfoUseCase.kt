package com.mrmnk.newsappremastered.domain

import javax.inject.Inject

class GetNewsInfoUseCase @Inject constructor (
    private val repository: NewsRepository
) {
    operator fun invoke(title: String) = repository.getNewsInfo(title)
}
