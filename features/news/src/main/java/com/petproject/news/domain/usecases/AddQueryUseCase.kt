package com.petproject.news.domain.usecases

import com.petproject.core.repository.QueryRepository
import javax.inject.Inject

class AddQueryUseCase @Inject constructor(
    private val queryRepository: QueryRepository
) {

    suspend operator fun invoke(query: String) {
        queryRepository.saveQuery(query)
    }
}