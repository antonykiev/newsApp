package com.internet.connection.news_detailed.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.internet.connection.news_detailed.domain.data.ArticlePresentation
import com.internet.connection.news_detailed.ui.screenstate.ScreenState

@Composable
fun NewsDetailedScreen(
    articleId: Long,
) {
    val viewModel = hiltViewModel<NewsDetailedViewModel>()

    LaunchedEffect(Unit) {
        viewModel.load(articleId)
    }

    val screenState by viewModel.state.collectAsStateWithLifecycle()

    when (val state = screenState) {
        is ScreenState.Error -> ErrorStateScreen(state)
        is ScreenState.Loaded -> LoadedStateScreen(state)
        ScreenState.Loading -> LoadingStateScreen(state)
    }
}

@Composable
fun LoadedStateScreen(
    state: ScreenState.Loaded,
    modifier: Modifier = Modifier
) {

}

@Composable
fun LoadingStateScreen(
    state: ScreenState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}



@Composable
fun ErrorStateScreen(
    state: ScreenState.Error,
    modifier: Modifier = Modifier
) {


}

@Preview(
    showSystemUi = true
)
@Composable
fun LoadedStateScreenPreview() {
    LoadedStateScreen(
        ScreenState.Loaded(
            ArticlePresentation(
                id = "id0".hashCode().toLong(),
                author = "Joel Khalili",
                description = LoremIpsum(20).toString(),
                content = LoremIpsum(100).toString(),
                urlToImage = "https://media.wired.com/photos/6703eb3979f13fda7f04485b/191:100/w_1280,c_limit/Satoshi-Nakamoto-biz-1341874258.jpg",
                title = LoremIpsum(5).toString(),
                publishedAt = "2024-10-09T01:00:00Z",
                url = "https://www.wired.com/story/unmasking-bitcoin-creator-satoshi-nakamoto-again/"
            )
        )
    )
}

