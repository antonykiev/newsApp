package com.petproject.core.repository.source

import com.petproject.server.api.EverythingApi
import com.petproject.server.response.EverythingResponse
import com.petproject.server.source.ArticleRemoteDataSource

class EverythingDataSourceImpl(private val api: EverythingApi) : ArticleRemoteDataSource {

    override suspend fun everything(keyword: String): Result<EverythingResponse> {
        return runCatching {
            api.everything(keyword)
        }
    }
}