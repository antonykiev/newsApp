package com.petproject.news.ui.screenstate

import com.petproject.news.domain.data.NewsPresentation

sealed interface ScreenState {

    data class ErrorLoading(
        val throwable: Throwable,
    ) : ScreenState

    data object Loading : ScreenState

    data class Loaded(
        val news: List<NewsPresentation>,
    ) : ScreenState

}
