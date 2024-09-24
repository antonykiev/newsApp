package com.petproject.news_list.usecase

import com.petproject.core.ScreenState
import com.petproject.news.ui.NewsScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ScreenStateUseCase @Inject constructor(
    //private val everythingNews: EverythingNewsUseCase
): ScreenState<NewsScreenState> {

    private var isLoading = false

    private val _state = MutableStateFlow<NewsScreenState>(NewsScreenState.Loading)
    override val state: StateFlow<NewsScreenState> = _state.asStateFlow()

    suspend operator fun invoke(): StateFlow<NewsScreenState> {
        return if (isLoading) {
            state
        } else {
            load()
            isLoading = true
            state
        }
    }

    private suspend fun load() {
        //everythingNews()
    }
}