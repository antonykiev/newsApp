package com.petproject.server.source

import com.petproject.server.api.Everything
import com.petproject.server.response.EverythingResponse

class EverythingDataSource(
    private val api: Everything
) {

    suspend fun everything(): EverythingResponse = api.everything()

}