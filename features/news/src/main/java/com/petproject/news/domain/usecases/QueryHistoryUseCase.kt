package com.petproject.news.domain.usecases

import com.petproject.core.data.Query
import com.petproject.core.repository.QueryRepository
import javax.inject.Inject

class QueryHistoryUseCase @Inject constructor(
    private val repository: QueryRepository,
) {
    suspend operator fun invoke(
        isActive: Boolean,
    ): Result<List<Query>> {
        return runCatching {
            if (isActive.not()) return@runCatching emptyList()
            repository.getRecentQueries()
        }
    }
}