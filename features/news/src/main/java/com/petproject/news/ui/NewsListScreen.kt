package com.petproject.news.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.petproject.news.R
import com.petproject.news.ui.screenstate.ListState

@Composable
fun NewsListScreen(
    onNewsClick: (articleId: Long) -> Unit,
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
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            ListContent(
                onNewsClick = onNewsClick,
                screenState = screenState.listState,
            )
        }
    }
}

@Composable
fun ListContent(
    onNewsClick: (articleId: Long) -> Unit,
    screenState: ListState,
) {
    when (screenState) {
        is ListState.ErrorLoading -> {

        }

        is ListState.Loaded -> LoadedStateScreen(
            state = screenState,
            onNewsClick = onNewsClick,
        )

        is ListState.Loading -> LoadingStateScreen(screenState)
        ListState.Initial -> {

        }
    }
}

@Composable
fun LoadedStateScreen(
    state: ListState.Loaded,
    onNewsClick: (articleId: Long) -> Unit,
) {
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

