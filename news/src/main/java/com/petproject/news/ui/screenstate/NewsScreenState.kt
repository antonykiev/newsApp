package com.petproject.news.ui.screenstate

import androidx.compose.runtime.Composable


sealed class NewsScreenState(
    open val args: StateArgs,
) {

    @Composable
    abstract fun Handle()
}
