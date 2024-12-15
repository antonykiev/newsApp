package com.petproject.core.repository

import com.pet.database.entity.QueryEntity
import com.pet.database.source.QueryLocalDataSource
import com.petproject.core.data.Query
import com.petproject.core.mappers.QueryEntityToQueryMapper
import com.petproject.core.mappers.QueryToQueryEntityMapper

class QueryRepositoryImpl (
    private val queryLocalDataSource: QueryLocalDataSource
): QueryRepository {

    override suspend fun getRecentQueries(): List<Query> {
        return queryLocalDataSource.getRecentQueries()
            .map(QueryEntityToQueryMapper::query)
    }

    override suspend fun saveQuery(query: String) {
        val queryEntity = QueryEntity(text = query)
        queryLocalDataSource.saveQuery(queryEntity)
    }

    override suspend fun updateQuery(query: Query) {
        val queryEntity = QueryToQueryEntityMapper.queryEntity(query)
        queryLocalDataSource.updateQuery(queryEntity)
    }
}