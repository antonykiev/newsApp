package com.petproject.core.mappers

import com.petproject.core.data.Article
import com.petproject.server.response.ArticleResponse

class ArticleResponseToArticleMapper(
    private val response: ArticleResponse,
) {
    fun article(): Article {
        return Article(
            author = response.author.orEmpty(),
            content = response.content,
            description = response.description.orEmpty(),
            publishedAt = response.publishedAt,
            source = SourceResponseToSourceMapper(
                response = response.source,
            ).source(),
            title = response.title,
            url = response.url,
            urlToImage = response.urlToImage.orEmpty(),
        )
    }
}