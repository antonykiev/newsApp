package com.petproject.server

import com.petproject.server.response.EverythingResponse
import com.petproject.server.source.EverythingDataSource

class ServerRepositoryImpl(
    private val dataSource: EverythingDataSource,
) : ServerRepository {

    override suspend fun everything(): Result<EverythingResponse> {
        return runCatching {
            dataSource.everything()
        }
    }
}