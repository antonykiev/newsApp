package com.petproject.news.ui.data

data class NewsPresentation(
    val id: String,
    val author: String,
    val date: String,
    val title: String,
    val url: String,
    val imageUrl: String?,
)