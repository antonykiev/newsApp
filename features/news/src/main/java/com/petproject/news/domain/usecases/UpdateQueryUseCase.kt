package com.petproject.news.domain.usecases

import com.petproject.core.data.Query
import com.petproject.core.repository.QueryRepository
import javax.inject.Inject

class UpdateQueryUseCase @Inject constructor(
    private val queryRepository: QueryRepository,
) {

    suspend operator fun invoke(query: Query) {
        val newQuery = query.copy(timestamp = System.currentTimeMillis())
        queryRepository.updateQuery(newQuery)
    }
}