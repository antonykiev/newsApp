package com.petproject.core.mappers

import com.petproject.core.data.Source
import com.petproject.server.response.SourceResponse

class SourceResponseToSourceMapper(
    private val response: SourceResponse,
) {
    fun source(): Source {
        return Source.Data(
            id = response.id,
            name = response.name,
        )
    }
}
