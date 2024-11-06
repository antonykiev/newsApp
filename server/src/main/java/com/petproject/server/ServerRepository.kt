package com.petproject.server

import com.petproject.data.Everything

interface ServerRepository {
    suspend fun everything(
        keyword: String,
    ): Result<Everything>
}