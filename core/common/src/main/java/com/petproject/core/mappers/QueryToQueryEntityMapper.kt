package com.petproject.core.mappers

import com.pet.database.entity.QueryEntity
import com.petproject.core.data.Query

object QueryToQueryEntityMapper {

    fun queryEntity(queryEntity: Query): QueryEntity {
        return QueryEntity(
            id = queryEntity.id,
            text = queryEntity.text,
            timestamp = queryEntity.timestamp
        )
    }
}