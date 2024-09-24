package com.petproject.server

import com.petproject.server.response.EverythingResponse

interface ServerRepository {
    suspend fun everything(): Result<EverythingResponse>
}