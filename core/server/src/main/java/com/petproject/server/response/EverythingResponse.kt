package com.petproject.server.response

data class EverythingResponse(
    val status: String,
    val totalResults: Long,
    val articles: List<ArticleResponse>
)
