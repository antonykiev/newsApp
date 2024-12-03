package com.internet.connection.news_detailed.ui.screenstate

import com.internet.connection.news_detailed.domain.data.NewsPresentation

sealed interface ScreenState {

    data class ErrorLoading(
        val throwable: Throwable,
    ) : ScreenState

    data object Loading : ScreenState

    data class Loaded(
        val news: NewsPresentation,
    ) : ScreenState

}