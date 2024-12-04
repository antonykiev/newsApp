package com.internet.connection.news_detailed.domain.mappers

import com.internet.connection.news_detailed.domain.data.ArticlePresentation
import com.petproject.core.data.Article

object ArticleToArticlePresentationMapper {

    operator fun invoke(article: Article): ArticlePresentation {
        return ArticlePresentation(
            id = article.id,
            author = article.author,
            content = article.content,
            description = article.description,
            publishedAt = article.publishedAt,
            source = article.source,
            title = article.title,
            url = article.url,
            urlToImage = article.urlToImage,
        )
    }
}