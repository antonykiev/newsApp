package com.petproject.news.ui.screenstate

import androidx.compose.runtime.Composable

data class ErrorLoading(override val args: StateArgs.ErrorLoadingArgs) : NewsScreenState(args) {
    @Composable
    override fun Handle() {

    }
}