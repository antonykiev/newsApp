package com.petproject.news.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.petproject.news.domain.data.NewsPresentation

private val ICON_SIZE = 84.dp
private val IMAGE_CORNER_RADIUS = ICON_SIZE / 2

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NewsItem(
    item: NewsPresentation,
    onItemClick: (articleId: Long) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(16.dp)
            )
            .background(Color(0xFFE8E2F8))
            .clickable {
                onItemClick(item.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .height(ICON_SIZE)
                .width(ICON_SIZE)
                .clip(
                    RoundedCornerShape(
                        topEnd = IMAGE_CORNER_RADIUS,
                        topStart = IMAGE_CORNER_RADIUS,
                        bottomEnd = IMAGE_CORNER_RADIUS,
                        bottomStart = IMAGE_CORNER_RADIUS
                    )
                )
                .sharedElement(
                    state = rememberSharedContentState(key = "image/${item.imageUrl}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(durationMillis = 250)
                    }
                ),
            model = item.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Column(
            modifier = Modifier.padding(start = 12.dp),
        ) {
            Text(
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 12.dp),
                text = item.date,
                maxLines = 1,
            )
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                text = item.author,
            )
            Text(
                text = item.title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
fun NewsItemPreview(
    @PreviewParameter(NewsPresentationProvider::class) newsItem: NewsPresentation,
) {
    SharedTransitionLayout {
        AnimatedVisibility(visible = true) {
            NewsItem(newsItem, {}, this)
        }
    }
}