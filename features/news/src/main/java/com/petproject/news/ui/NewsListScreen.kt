package com.petproject.news.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    val screenState by viewModel.state.collectAsStateWithLifecycle()

    Log.d("NewsListScreen", "screenState: $screenState")

    Scaffold(
        topBar = {
            SearchBar(
                query = screenState.searchBarState.query,
                onQueryChange = {
                    viewModel.onQueryChange(it)
                },
                onSearch = {
                    viewModel.onSearch(it)
                },
                active = screenState.searchBarState.active,
                onActiveChange = {
                    viewModel.onActiveChange(it)
                },
                placeholder = {
                    Text(text = stringResource(R.string.enter_your_query))
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                colors = SearchBarDefaults.colors(
                    dividerColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

            }
        },
        content = { innerPadding ->

            Log.d("NewsListScreen", "innerPadding: $innerPadding")
            ListContent(
                innerPadding = innerPadding,
                onNewsClick = onNewsClick,
                screenState = screenState.listState,
            )
        }
    )
}

@Composable
fun ListContent(
    onNewsClick: (articleId: Long) -> Unit,
    screenState: ListState,
    innerPadding: PaddingValues,
) {
    when (screenState) {
        is ListState.ErrorLoading -> {

        }

        is ListState.Loaded -> LoadedStateScreen(
            innerPadding = innerPadding,
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
    innerPadding: PaddingValues,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            horizontal = 8.dp
        ),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        itemsIndexed(
            items = state.news,
            key = { _, it -> it.title }
        ) { index, item ->
            val modifier = Modifier.calculateModifier(index, innerPadding, state)
            NewsItem(
                modifier = modifier,
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

private fun Modifier.calculateModifier(
    index: Int,
    innerPadding: PaddingValues,
    state: ListState.Loaded,
): Modifier {
    return when (index) {
        0 -> padding(top = innerPadding.calculateTopPadding())
        state.news.lastIndex -> padding(bottom = 16.dp)
        else -> this
    }
}

