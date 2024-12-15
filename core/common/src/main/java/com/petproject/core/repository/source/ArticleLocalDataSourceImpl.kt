package com.petproject.core.repository.source

import com.pet.database.dao.ArticleDao
import com.pet.database.entity.ArticleEntity
import com.pet.database.source.ArticleLocalDataSource
import kotlinx.coroutines.flow.Flow

class ArticleLocalDataSourceImpl(
    private val articleDao: ArticleDao,
) : ArticleLocalDataSource {

    override suspend fun insertArticles(listArticleEntity: List<ArticleEntity>) {
        val articlesDb = articleDao.getAllArticlesByIdList(
            idList = listArticleEntity.map { it.id }
        )

        val merged = listArticleEntity.map { articleToSave ->
            val found = articlesDb.find { it.id == articleToSave.id }

            found ?: return@map articleToSave

            if (found.keyWords.containsAll(articleToSave.keyWords)) return@map null

            return@map found.let {
                articleToSave.copy(
                    keyWords = (articleToSave.keyWords + it.keyWords).distinct()
                )
            }
        }.filterNotNull()

        articleDao.update(merged)
    }

    override suspend fun article(articleId: Long): Result<ArticleEntity> {
        return runCatching {
            articleDao.article(articleId)
        }
    }

    override fun observeArticles(keyword: String): Flow<List<ArticleEntity>> {
        return articleDao.observeArticles(keyword)
    }

    override suspend fun everything(keyword: String): Result<List<ArticleEntity>> {
        return runCatching {
            articleDao.getAllArticles()
        }
    }
}