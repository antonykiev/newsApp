package com.petproject.news.ui

import java.lang.Exception

sealed class NewsScreenState {

    data object Loading : NewsScreenState()

    class ErrorLoading(
        val exception: Exception
    ) : NewsScreenState()

    data class Loaded(
        val news: List<NewsItem>
    ) : NewsScreenState()

}