package com.internet.connection.news_detailed.domain.data

data class ArticlePresentation(
    val id: Long,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String,
)
