package com.petproject.server.response

data class ArticlesResponse(
    val status: String,
    val totalResults: Long,
    val articles: List<ArticleResponse>
)
