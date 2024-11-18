package com.petproject.news.ui.mappers

import com.petproject.core.data.Article
import com.petproject.news.ui.data.NewsPresentation
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class ArticleToNewsPresentationMapper(
    private val article: Article,
) {
    fun newsPresentation(): NewsPresentation {
        return NewsPresentation(
            id = article.publishedAt,
            imageUrl = article.urlToImage.orEmpty(),
            title = article.title,
            author = article.author.orEmpty(),
            date = formatIsoTimestamp(article.publishedAt),
            url = article.url,
        )
    }

    private fun formatIsoTimestamp(isoTimestamp: String): String {
        return runCatching {
            val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            isoFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date = isoFormat.parse(isoTimestamp)
            val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            outputFormat.timeZone = TimeZone.getDefault()
            date?.let { outputFormat.format(it) }.orEmpty()
        }.getOrDefault("")
    }
}