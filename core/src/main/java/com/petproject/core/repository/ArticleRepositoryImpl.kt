package com.petproject.core.repository

import com.pet.database.source.ArticleLocalDataSource
import com.petproject.core.data.Article
import com.petproject.core.mappers.ArticleListToListArticleEntityMapper
import com.petproject.core.mappers.EverythingResponseToEverythingMapper
import com.petproject.core.mappers.ListArticleEntityToArticleMapper
import com.petproject.server.source.ArticleRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ArticleRepositoryImpl(
    private val dataSourceRemote: ArticleRemoteDataSource,
    private val dataSourceLocal: ArticleLocalDataSource,
) : ArticleRepository {

    override suspend fun everything(keyword: String): Flow<Result<List<Article>>> {
        return channelFlow {
            launch {
                dataSourceRemote.everything(keyword).map {
                    EverythingResponseToEverythingMapper(it).everything()
                }.map {
                    it.articles
                }.onSuccess {
                    dataSourceLocal.insertArticles(
                        ArticleListToListArticleEntityMapper(it).listArticleEntity()
                    )
                }.apply {
                    send(this)
                }
            }

            launch {
                val localResult = dataSourceLocal.everything(keyword).map {
                    ListArticleEntityToArticleMapper(it).articleList()
                }
                send(localResult)
            }
        }.distinctUntilChanged()
    }
}