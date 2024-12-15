package com.petproject.news.ui.screenstate

import com.petproject.core.data.Query
import com.petproject.news.domain.data.NewsPresentation


data class ScreenState(
    val searchBarState: SearchBarState,
    val listState: ListState,
)

sealed interface ListState {

    data object Initial: ListState

    data object Loading : ListState

    data class Loaded(
        val news: List<NewsPresentation>,
    ) : ListState

    data class ErrorLoading(
        val throwable: Throwable,
    ) : ListState

}

data class SearchBarState(
    val queryHistory: List<Query>,
    val query: String,
    val active: Boolean,
)
