package com.internet.connection.news_detailed.domain.data

import com.petproject.core.data.Source

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
