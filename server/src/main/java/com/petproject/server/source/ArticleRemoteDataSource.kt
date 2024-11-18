package com.petproject.server.source

import com.petproject.server.response.EverythingResponse

interface ArticleRemoteDataSource {
    suspend fun everything(keyword: String): Result<EverythingResponse>
}