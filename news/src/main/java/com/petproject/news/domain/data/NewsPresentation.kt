package com.petproject.news.domain.data

data class NewsPresentation(
    val id: Long,
    val author: String,
    val date: String,
    val title: String,
    val url: String,
    val imageUrl: String,
)