package com.internet.connection.news_detailed.ui.screenstate

import com.internet.connection.news_detailed.domain.data.ArticlePresentation

sealed interface ScreenState {

    data class Error(
        val throwable: Throwable,
    ) : ScreenState

    data object Loading : ScreenState

    data class Loaded(
        val news: ArticlePresentation,
    ) : ScreenState

}