package com.pet.database.source

import com.pet.database.entity.ArticleEntity

interface ArticleLocalDataSource {
    suspend fun everything(keyword: String): Result<List<ArticleEntity>>
    suspend fun insertArticles(listArticleEntity: List<ArticleEntity>)
}