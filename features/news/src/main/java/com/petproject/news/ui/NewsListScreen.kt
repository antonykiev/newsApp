package com.petproject.news.ui

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.petproject.news.R
import com.petproject.news.ui.screenstate.ListState
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

    Row(
        modifier = Modifier.fillMaxSize(),
    ) {
        SearchBar(
            query = screenState.searchBarState.query,
            onQueryChange = {
                viewModel.onQueryChange(it)
            },
            onSearch = {
                viewModel.onSearch(it)
            },
            active = screenState.searchBarState.active,
            onActiveChange = { },
            placeholder = {
                Text(text = stringResource(R.string.enter_your_query))
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            when (val state = screenState.listState) {
                is ListState.ErrorLoading -> {

                }

                is ListState.Loaded -> LoadedStateScreen(
                    state = state,
                    onNewsClick = onNewsClick,
                    animatedVisibilityScope = animatedVisibilityScope
                )

                is ListState.Loading -> LoadingStateScreen(state)
                ListState.Initial -> {

                }
            }
        }
    }
}



@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.LoadedStateScreen(
    state: ListState.Loaded,
    onNewsClick: (articleId: Long) -> Unit,
    animatedVisibilityScope: AnimatedContentScope,
) {
    Log.d("NewsListScreen", "LoadedStateScreen: $state")

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            vertical = 16.dp,
            horizontal = 8.dp
        ),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
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

@Composable
fun LoadingStateScreen(state: ListState.Loading) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}
