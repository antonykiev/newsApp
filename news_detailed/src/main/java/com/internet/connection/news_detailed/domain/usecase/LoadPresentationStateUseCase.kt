package com.internet.connection.news_detailed.domain.usecase

import com.internet.connection.news_detailed.domain.data.ArticlePresentation
import com.internet.connection.news_detailed.domain.mappers.ArticleToArticlePresentationMapper
import com.petproject.core.data.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadPresentationStateUseCase @Inject constructor(
    private val articleUseCase: ArticleUseCase,
    private val validContentUseCase: ValidContentUseCase,
    private val contentFromHtmlUseCase: ContentFromHtmlUseCase,
) {

    suspend operator fun invoke(
        articleId: Long,
    ): Result<ArticlePresentation> {
        return article(articleId)
            .map { article -> articlePresentation(article) }
    }

    private suspend fun article(articleId: Long,) = withContext(Dispatchers.IO) {
        articleUseCase(articleId)
    }

    private suspend fun articlePresentation(article: Article): ArticlePresentation {
        return withContext(Dispatchers.Default) {
            ArticleToArticlePresentationMapper(article)
                .copy(content = validContentUseCase(article.content))
        }
    }
}