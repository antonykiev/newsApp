package com.petproject.core.repository

import com.petproject.core.data.Query

interface QueryRepository {
    suspend fun getRecentQueries(): List<Query>
    suspend fun saveQuery(query: String)
}