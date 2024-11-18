package com.petproject.core.repository

import com.petproject.core.data.Everything
import com.petproject.core.mappers.EverythingResponseToEverythingMapper
import com.pet.database.source.ArticleLocalDataSource

class ArticleRepositoryImpl(
    private val dataSourceRemote: com.petproject.server.source.ArticleRemoteDataSource,
    private val dataSourceLocal: ArticleLocalDataSource,
) : ArticleRepository {

    override suspend fun everything(keyword: String): Result<Everything> {
        return dataSourceRemote.everything(keyword).map {
            EverythingResponseToEverythingMapper(it).everything()
        }
    }
}