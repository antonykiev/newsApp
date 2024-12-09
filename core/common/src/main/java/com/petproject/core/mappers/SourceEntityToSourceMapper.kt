package com.petproject.core.mappers

import com.pet.database.entity.SourceEntity
import com.petproject.core.data.Source

class SourceEntityToSourceMapper(
    private val sourceEntity: SourceEntity?,
) {

    fun source(): Source {
        if (sourceEntity == null) return Source.Empty
        return Source.Data(
            id = sourceEntity.id,
            name = sourceEntity.name,
        )
    }
}