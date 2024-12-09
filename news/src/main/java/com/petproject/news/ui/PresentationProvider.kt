package com.petproject.news.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.petproject.news.domain.data.NewsPresentation

class NewsPresentationProvider : PreviewParameterProvider<NewsPresentation> {
    override val values = sequenceOf(
        NewsPresentation(
            id = "id0".hashCode().toLong(),
            imageUrl = "https://media.wired.com/photos/6703eb3979f13fda7f04485b/191:100/w_1280,c_limit/Satoshi-Nakamoto-biz-1341874258.jpg",
            title = "Unmasking Bitcoin Creator Satoshi Nakamoto—Again",
            author = "Joel Khalili",
            date = "2024-10-09T01:00:00Z",
            url = "https://www.wired.com/story/unmasking-bitcoin-creator-satoshi-nakamoto-again/"
        )
    )
}