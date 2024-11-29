package com.pet.database.source

import com.pet.database.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface ArticleLocalDataSource {
    suspend fun everything(keyword: String): Result<List<ArticleEntity>>
    suspend fun insertArticles(listArticleEntity: List<ArticleEntity>)
    fun observeArticles(keyword: String): Flow<List<ArticleEntity>>
}