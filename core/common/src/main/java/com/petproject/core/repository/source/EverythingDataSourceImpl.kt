package com.petproject.core.repository.source

import com.petproject.server.api.RemoteApi
import com.petproject.server.response.EverythingResponse
import com.petproject.server.source.ArticleRemoteDataSource

class EverythingDataSourceImpl(private val api: RemoteApi) : ArticleRemoteDataSource {

    override suspend fun everything(keyword: String): Result<EverythingResponse> {
        return runCatching {
            api.everything(keyword)
        }
    }

    override suspend fun article(url: String): Result<String> {
        return runCatching {
            api.article(url)
        }
    }
}