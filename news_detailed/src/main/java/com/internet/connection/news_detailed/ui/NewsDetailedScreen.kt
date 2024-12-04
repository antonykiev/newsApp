package com.internet.connection.news_detailed.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.internet.connection.news_detailed.ui.screenstate.ScreenState

@Composable
fun NewsDetailedScreen(
    articleId: Long,
) {
    val viewModel = hiltViewModel<NewsDetailedViewModel>()

    LaunchedEffect(Unit) {
        viewModel.load(articleId)
    }

    val state: ScreenState by viewModel.state.collectAsStateWithLifecycle()
    Log.d("NewsDetailedScreen", "state: $state")
}