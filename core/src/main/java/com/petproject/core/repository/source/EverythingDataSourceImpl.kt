package com.petproject.core.repository.source

import android.util.Log
import com.petproject.server.api.EverythingApi
import com.petproject.server.response.EverythingResponse
import com.petproject.server.source.ArticleRemoteDataSource

class EverythingDataSourceImpl(private val api: EverythingApi) : ArticleRemoteDataSource {

    override suspend fun everything(keyword: String): Result<EverythingResponse> {
        return runCatching {
            api.everything(keyword)
        }.apply {
            Log.d("NewsViewModel", "everything ${this.onFailure { it }}")
        }
    }

    override suspend fun article(url: String): Result<String> {
        return runCatching {
            api.article(url)
        }.apply {
            Log.d("NewsViewModel", "article ${this.onFailure { it }}")
        }
    }
}