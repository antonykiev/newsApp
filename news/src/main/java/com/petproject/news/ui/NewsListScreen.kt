package com.petproject.news.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.petproject.news.NewsViewModel
import com.petproject.news.ui.screenstate.NewsScreenState

@Composable
fun NewsListScreen(
    onNewsClick: (String) -> Unit,
) {
    val viewModel = hiltViewModel<NewsViewModel>()

    LaunchedEffect(Unit) {
        viewModel.loadInitialState()
    }

    val state: NewsScreenState by viewModel.state.collectAsStateWithLifecycle()

    when (val screenState = state) {
        is NewsScreenState.ErrorLoading -> {

        }
        is NewsScreenState.Loaded -> {
            LazyColumn {
                items(
                    items = screenState.news,
                    key = { it.title }
                ) { item ->
                    NewsItem(item)
                    HorizontalDivider(
                        color = Color.Gray,
                        thickness = 1.dp
                    )
                }
            }
        }
        NewsScreenState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

