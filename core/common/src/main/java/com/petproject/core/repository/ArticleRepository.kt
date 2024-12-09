package com.petproject.core.repository

import com.petproject.core.data.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun remoteArticles(keyword: String): Result<List<Article>>
    suspend fun saveLocalArticles(articleList: List<Article>, keyword: String)
    suspend fun article(articleId: Long): Result<Article>

    fun observeLocalArticles(keyword: String): Flow<List<Article>>
    fun observeArticle(id: Long): Flow<Article>
}