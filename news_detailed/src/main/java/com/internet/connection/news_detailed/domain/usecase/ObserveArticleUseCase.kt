package com.internet.connection.news_detailed.domain.usecase

import com.petproject.core.data.Article
import com.petproject.core.repository.ArticleRepository
import javax.inject.Inject

class ArticleUseCase @Inject constructor(
    private val serverRepository: ArticleRepository,
) {

    suspend operator fun invoke(articleId: Long): Result<Article> {
        return serverRepository.article(articleId)
    }
}