package com.petproject.core.repository

import com.petproject.core.data.Everything

interface ArticleRepository {
    suspend fun everything(
        keyword: String,
    ): Result<Everything>
}