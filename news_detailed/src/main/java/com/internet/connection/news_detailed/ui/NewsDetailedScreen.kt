package com.internet.connection.news_detailed.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.internet.connection.news_detailed.R
import com.internet.connection.news_detailed.domain.data.ArticlePresentation
import com.internet.connection.news_detailed.ui.screenstate.ScreenState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NewsDetailedScreen(
    onBackClick: () -> Unit,
    articleId: Long,
    animatedVisibilityScope: AnimatedContentScope,
) {
    val viewModel = hiltViewModel<NewsDetailedViewModel>()

    LaunchedEffect(Unit) {
        viewModel.load(articleId)
    }

    val screenState by viewModel.state.collectAsStateWithLifecycle()

    when (val state = screenState) {
        is ScreenState.Error -> ErrorStateScreen(state)
        is ScreenState.Loaded -> LoadedStateScreen(
            state = state,
            onBackClick = onBackClick,
            animatedVisibilityScope = animatedVisibilityScope
        )
        ScreenState.Loading -> LoadingStateScreen(state)
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.LoadedStateScreen(
    state: ScreenState.Loaded,
    onBackClick: () -> Unit,
    animatedVisibilityScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .sharedElement(
                        state = rememberSharedContentState(key = "image/${state.news.urlToImage}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 250)
                        }
                    ),
                model = state.news.urlToImage,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            bounded = false, // For ripple beyond the icon bounds
                            radius = 28.dp,
                            color = Color.Gray
                        ),
                        onClick = onBackClick
                    )
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector  = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = Color.White
                )
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            text = state.news.title,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium,
                text = stringResource(R.string.author, state.news.author),
                fontWeight = FontWeight.Bold,
            )

            Text(
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium,
                text = stringResource(R.string.published_at, state.news.publishedAt),
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            text = stringResource(R.string.description)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            text = state.news.description
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            text = state.news.content
        )
    }
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
//    LoadedStateScreen(
//        state = ScreenState.Loaded(
//            ArticlePresentation(
//                id = "id0".hashCode().toLong(),
//                author = "Joel Khalili",
//                description = LoremIpsum(20).toString(),
//                content = LoremIpsum(100).toString(),
//                urlToImage = "https://media.wired.com/photos/6703eb3979f13fda7f04485b/191:100/w_1280,c_limit/Satoshi-Nakamoto-biz-1341874258.jpg",
//                title = LoremIpsum(5).toString(),
//                publishedAt = "15 March 2024",
//                url = "https://www.wired.com/story/unmasking-bitcoin-creator-satoshi-nakamoto-again/"
//            )
//        ),
//        onBackClick = {}
//    )
}

