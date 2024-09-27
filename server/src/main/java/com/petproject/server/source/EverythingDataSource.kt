package com.petproject.server.source

import com.petproject.server.api.Everything
import com.petproject.server.response.EverythingResponse
import retrofit2.http.Field

class EverythingDataSource(private val api: Everything) {

    suspend fun everything(keyword: String,): Result<EverythingResponse> {
        return runCatching {
            api.everything(keyword)
        }
    }
}