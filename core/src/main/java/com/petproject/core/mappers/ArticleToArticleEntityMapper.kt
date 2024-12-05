package com.petproject.core.mappers

import com.pet.database.entity.ArticleEntity
import com.petproject.core.data.Article

class ArticleToArticleEntityMapper(
    private val article: Article,
    private val keyword: String
) {

    fun articleEntity(): ArticleEntity {
        return ArticleEntity(
            id = article.id,
            author = article.author,
            content = article.content,
            description = article.description,
            publishedAt = article.publishedAt,
            source = SourceToSourceEntityMapper(article.source).sourceEntity(),
            title = article.title,
            url = article.url,
            urlToImage = article.urlToImage,
            keyWords = listOf(keyword)
        )
    }
}