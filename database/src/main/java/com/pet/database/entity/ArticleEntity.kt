package com.pet.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey
    val id: Long,
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceEntity?,
    val title: String,
    val url: String,
    val urlToImage: String?,
)
