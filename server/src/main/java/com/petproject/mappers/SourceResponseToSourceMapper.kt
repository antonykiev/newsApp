package com.petproject.mappers

import com.petproject.data.Source
import com.petproject.server.response.SourceResponse

class SourceResponseToSourceMapper(
    private val response: SourceResponse,
) {
    fun source(): Source {
        return Source(
            id = response.id,
            name = response.name,
        )
    }
}
