package com.petproject.core.data

data class Everything(
    val status: String,
    val totalResults: Long,
    val articles: List<Article>,
)
