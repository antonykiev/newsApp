package com.petproject.news.ui.screenstate

import com.petproject.news.ui.data.NewsPresentation


sealed interface NewsScreenState {

    data class ErrorLoading(
        val throwable: Throwable,
    ) : NewsScreenState

    data object Loading : NewsScreenState

    data class Loaded(
        val news: List<NewsPresentation>,
    ) : NewsScreenState

}
