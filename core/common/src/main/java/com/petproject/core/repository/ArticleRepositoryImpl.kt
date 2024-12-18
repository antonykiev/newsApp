package com.petproject.core.repository

import com.pet.database.source.ArticleLocalDataSource
import com.petproject.core.data.Article
import com.petproject.core.mappers.ArticleEntityToArticleMapper
import com.petproject.core.mappers.ArticleListToListArticleEntityMapper
import com.petproject.core.mappers.EverythingResponseToEverythingMapper
import com.petproject.core.mappers.ListArticleEntityToListArticleMapper
import com.petproject.server.source.ArticleRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticleRepositoryImpl(
    private val dataSourceRemote: ArticleRemoteDataSource,
    private val dataSourceLocal: ArticleLocalDataSource,
) : ArticleRepository {

    override suspend fun remoteArticles(keyword: String): Result<List<Article>> {
        return dataSourceRemote.everything(keyword)
            .map {
                EverythingResponseToEverythingMapper(it)
                    .everything()
                    .articles
                    .filter { article -> article.author.isNotBlank() &&  article.author.isNotEmpty() }
            }
    }

    override suspend fun saveLocalArticles(articleList: List<Article>, keyword: String) {
        dataSourceLocal.insertArticles(
            ArticleListToListArticleEntityMapper(
                articleList = articleList,
                keyword = keyword
            ).listArticleEntity()
        )
    }

    override suspend fun article(articleId: Long): Result<Article> {
        return dataSourceLocal.article(articleId)
            .map {
                ArticleEntityToArticleMapper(it)
                    .article()
            }
    }

    override fun observeLocalArticles(keyword: String): Flow<List<Article>> {
        return dataSourceLocal.observeArticles(keyword)
            .map(ListArticleEntityToListArticleMapper::articleList)
    }

    override fun observeArticle(id: Long): Flow<Article> {
        TODO("Not yet implemented")
    }
}