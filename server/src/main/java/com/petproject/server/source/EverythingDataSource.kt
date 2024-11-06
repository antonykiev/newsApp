package com.petproject.server.source

import com.petproject.data.Everything
import com.petproject.mappers.EverythingResponseToEverythingMapper
import com.petproject.server.api.EverythingApi
import com.petproject.server.response.EverythingResponse

class EverythingDataSource(private val api: EverythingApi) {

    suspend fun everything(keyword: String,): Result<Everything> {
        return runCatching {
            EverythingResponseToEverythingMapper(
                response = api.everything(keyword)
            ).everything()
        }
    }
}