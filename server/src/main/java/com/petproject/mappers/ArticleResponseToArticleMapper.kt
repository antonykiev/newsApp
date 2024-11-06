package com.petproject.mappers

import com.petproject.data.Article
import com.petproject.server.response.ArticleResponse

class ArticleResponseToArticleMapper(
    private val response: ArticleResponse,
) {
    fun article(): Article {
        return Article(
            author = response.author,
            content = response.content,
            description = response.description,
            publishedAt = response.publishedAt,
            source = SourceResponseToSourceMapper(
                response = response.source,
            ).source(),
            title = response.title,
            url = response.url,
            urlToImage = response.urlToImage,
        )
    }
}