package com.petproject.news.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.petproject.news.ui.screenstate.ScreenState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NewsListScreen(
    onNewsClick: (articleId: Long) -> Unit,
    animatedVisibilityScope: AnimatedContentScope,
) {
    val viewModel = hiltViewModel<NewsViewModel>()

    LaunchedEffect(Unit) {
        viewModel.loadInitialState()
    }

    val screenState by viewModel.state.collectAsStateWithLifecycle()

    when (val state = screenState) {
        is ScreenState.ErrorLoading -> {

        }

        is ScreenState.Loaded -> {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    vertical = 16.dp,
                    horizontal = 8.dp
                )
            ) {
                items(
                    items = state.news,
                    key = { it.title }
                ) { item ->
                    NewsItem(
                        item = item,
                        onItemClick = onNewsClick,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
            }
        }

        ScreenState.Loading -> {
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

