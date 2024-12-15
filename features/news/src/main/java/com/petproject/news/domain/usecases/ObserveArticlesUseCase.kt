package com.petproject.news.domain.usecases

import com.petproject.core.repository.ArticleRepository
import com.petproject.news.domain.data.NewsPresentation
import com.petproject.news.domain.mappers.ArticleToNewsPresentationMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

class ObserveArticlesUseCase @Inject constructor(
    private val serverRepository: ArticleRepository,
) {

    suspend operator fun invoke(query: String): Flow<List<NewsPresentation>> {
        return channelFlow {
            //remote
            launch(Dispatchers.IO) {
                serverRepository.remoteArticles(query)
                    .map { serverRepository.saveLocalArticles(it, query) }
            }
            //local
            launch(Dispatchers.IO) {
                serverRepository.observeLocalArticles(query)
                    .map { it.map(ArticleToNewsPresentationMapper::newsPresentation)}
                    .collect(::trySend)
            }
        }
    }
}