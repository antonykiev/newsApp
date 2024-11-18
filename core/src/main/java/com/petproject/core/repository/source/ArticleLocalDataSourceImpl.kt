package com.petproject.core.repository.source

import com.pet.database.dao.ArticleDao
import com.pet.database.entity.ArticleEntity
import com.pet.database.source.ArticleLocalDataSource

class ArticleLocalDataSourceImpl(
    private val articleDao: ArticleDao,
) : ArticleLocalDataSource {

    suspend fun insertArticles(articles: List<ArticleEntity>) {
        articleDao.insert(articles)
    }

    override suspend fun everything(keyword: String): Result<List<ArticleEntity>> {
        TODO("Not yet implemented")
    }
}