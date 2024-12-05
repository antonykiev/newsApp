package com.petproject.news.domain.usecases

import com.petproject.core.repository.ArticleRepository
import com.petproject.news.domain.data.NewsPresentation
import com.petproject.news.domain.mappers.ArticleToNewsPresentationMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class ObserveArticlesUseCase @Inject constructor(
    private val serverRepository: ArticleRepository,
) {
    // transform result to Result
    suspend operator fun invoke(): Flow<List<NewsPresentation>> {
        return channelFlow {
            val keyword = "bitcoin"
            launch(Dispatchers.IO) {
                serverRepository.remoteArticles(keyword)
                    .map { serverRepository.saveLocalArticles(it, keyword) }
            }
            launch(Dispatchers.IO) {
                serverRepository.observeLocalArticles(keyword)
                    .map { it.map(ArticleToNewsPresentationMapper::newsPresentation) }
                    .collect(::trySend)
            }
        }
    }
}