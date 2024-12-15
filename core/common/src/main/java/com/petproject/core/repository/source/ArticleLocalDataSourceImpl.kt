package com.petproject.core.repository.source

import com.pet.database.dao.ArticleDao
import com.pet.database.entity.ArticleEntity
import com.pet.database.source.ArticleLocalDataSource
import kotlinx.coroutines.flow.Flow

class ArticleLocalDataSourceImpl(
    private val articleDao: ArticleDao,
) : ArticleLocalDataSource {

    override suspend fun insertArticles(listArticleEntity: List<ArticleEntity>) {
        val resultList = articleDao.insert(listArticleEntity)
        if (!resultList.contains(-1)) return

        val articlesToSave = resultList.mapIndexed  { index, it ->
            if (it == -1L) {
                val articleNew = listArticleEntity[index]
                val articleDb = articleDao.article(articleNew.id)
                return@mapIndexed articleNew.copy(
                    keyWords = (articleNew.keyWords + articleDb.keyWords).distinct()
                )
            }
            return@mapIndexed null
        }.filterNotNull()
        articleDao.update(articlesToSave)
    }

    override suspend fun article(articleId: Long): Result<ArticleEntity> {
        return runCatching {
            articleDao.article(articleId)
        }
    }

    override fun observeArticles(keyword: String): Flow<List<ArticleEntity>> {
        return articleDao.observeArticles()
    }

    override suspend fun everything(keyword: String): Result<List<ArticleEntity>> {
        return runCatching {
            articleDao.getAllArticles()
        }
    }
}