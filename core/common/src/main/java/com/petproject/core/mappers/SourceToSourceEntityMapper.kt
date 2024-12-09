package com.petproject.core.mappers

import com.pet.database.entity.SourceEntity
import com.petproject.core.data.Source

class SourceToSourceEntityMapper(
    private val source: Source,
) {
    fun sourceEntity(): SourceEntity? {
        return when (source) {
            is Source.Data -> SourceEntity(
                id = source.id.orEmpty(),
                name = source.name,
            )

            is Source.Empty -> null

        }
    }
}