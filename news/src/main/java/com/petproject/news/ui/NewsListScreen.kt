package com.petproject.news.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.petproject.news.NewsViewModel

@Composable
fun NewsListScreen(
    onNewsClick: (String) -> Unit,
) {
    val viewModel = hiltViewModel<NewsViewModel>()

    viewModel.state
        .collectAsState()
        .value
        .Handle()

    LaunchedEffect(Unit) {
        viewModel.loadInitialState()
    }
}

