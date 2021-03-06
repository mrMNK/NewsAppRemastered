package com.mrmnk.newsappremastered.domain

import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke() = repository.loadData()
}