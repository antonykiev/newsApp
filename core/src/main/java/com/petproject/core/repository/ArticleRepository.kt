package com.petproject.core.repository

import com.petproject.core.data.Article
import com.petproject.core.data.Everything
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    suspend fun everything(
        keyword: String,
    ): Flow<Result<List<Article>>>
}