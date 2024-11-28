package com.petproject.core.repository.source

import com.pet.database.dao.ArticleDao
import com.pet.database.entity.ArticleEntity
import com.pet.database.source.ArticleLocalDataSource

class ArticleLocalDataSourceImpl(
    private val articleDao: ArticleDao,
) : ArticleLocalDataSource {

    override suspend fun insertArticles(listArticleEntity: List<ArticleEntity>) {
        articleDao.insert(listArticleEntity)
    }

    override suspend fun everything(keyword: String): Result<List<ArticleEntity>> {
        return runCatching {
            articleDao.getAllArticles()
        }
    }
}