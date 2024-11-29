package com.petproject.news.usecases

import com.petproject.core.data.Article
import com.petproject.core.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ObserveArticlesUseCase @Inject constructor(
    private val serverRepository: ArticleRepository,
) {
    // transform result to Result
    suspend fun invoke(): Flow<List<Article>> {
        return channelFlow {
            launch {
                serverRepository.remoteArticles("bitcoin")
                    .map { serverRepository.saveLocalArticles(it) }
            }
            launch {
                serverRepository.observeLocalArticles("bitcoin")
                    .collect(::trySend)
            }
        }
    }
}