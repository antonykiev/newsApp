package com.petproject.core.mappers

import com.pet.database.entity.QueryEntity
import com.petproject.core.data.Query

object QueryEntityToQueryMapper {

    fun query(queryEntity: QueryEntity): Query {
        return Query(
            id = queryEntity.id,
            text = queryEntity.query,
            timestamp = queryEntity.timestamp
        )
    }
}