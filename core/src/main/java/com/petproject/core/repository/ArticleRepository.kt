package com.petproject.core.repository

import com.petproject.core.data.Article
import com.petproject.core.data.Everything
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    suspend fun remoteArticles(keyword: String, ): Result<List<Article>>
    suspend fun saveLocalArticles(articleList: List<Article>)
    fun observeLocalArticles(keyword: String): Flow<List<Article>>
}