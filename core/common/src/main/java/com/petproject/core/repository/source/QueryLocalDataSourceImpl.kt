package com.petproject.core.repository.source

import com.pet.database.dao.QueryDao
import com.pet.database.entity.QueryEntity
import com.pet.database.source.QueryLocalDataSource

class QueryLocalDataSourceImpl(
    private val queryDao: QueryDao
): QueryLocalDataSource {

    override suspend fun getRecentQueries(): List<QueryEntity> {
        return queryDao.getRecentQueries()
    }

    override suspend fun saveQuery(query: QueryEntity) {
        queryDao.saveQuery(query)
    }

    override suspend fun updateQuery(query: QueryEntity) {
        queryDao.updateQuery(query)
    }
}