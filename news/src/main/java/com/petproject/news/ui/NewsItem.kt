package com.petproject.news.ui

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.petproject.news.ui.data.NewsPresentation

private val ICON_SIZE = 84.dp
private val IMAGE_CORNER_RADIUS = ICON_SIZE / 2

@Composable
fun NewsItem(
    item: NewsPresentation,
    onItemClick: (newsUrl: String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(item.imageUrl)
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

class NewsPresentationProvider : PreviewParameterProvider<NewsPresentation> {
    override val values = sequenceOf(
        NewsPresentation(
            id = "id0",
            imageUrl = "https://media.wired.com/photos/6703eb3979f13fda7f04485b/191:100/w_1280,c_limit/Satoshi-Nakamoto-biz-1341874258.jpg",
            title = "Unmasking Bitcoin Creator Satoshi Nakamoto—Again",
            author = "Joel Khalili",
            date = "2024-10-09T01:00:00Z",
            url = "https://www.wired.com/story/unmasking-bitcoin-creator-satoshi-nakamoto-again/"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun NewsItemPreview(
    @PreviewParameter(NewsPresentationProvider::class) newsItem: NewsPresentation,
) {
    NewsItem(newsItem) {

    }
}