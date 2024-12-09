package com.petproject.core.mappers

import com.pet.database.entity.ArticleEntity
import com.petproject.core.data.Article

class ArticleEntityToArticleMapper(
    private val articleEntity: ArticleEntity,
) {
    fun article(): Article {
        return Article(
            id = articleEntity.id,
            author = articleEntity.author.orEmpty(),
            content = articleEntity.content,
            description = articleEntity.description,
            publishedAt = articleEntity.publishedAt,
            source = SourceEntityToSourceMapper(articleEntity.source).source(),
            title = articleEntity.title,
            url = articleEntity.url,
            urlToImage = articleEntity.urlToImage.orEmpty()
        )
    }
}