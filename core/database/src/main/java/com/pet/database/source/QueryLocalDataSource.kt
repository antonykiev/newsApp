package com.pet.database.source

import com.pet.database.entity.QueryEntity

interface QueryLocalDataSource {
    suspend fun getRecentQueries(): List<QueryEntity>
    suspend fun saveQuery(query: QueryEntity)
}