package com.petproject.server.api

import com.petproject.server.response.EverythingResponse

interface Everything {
    suspend fun everything(): EverythingResponse
}