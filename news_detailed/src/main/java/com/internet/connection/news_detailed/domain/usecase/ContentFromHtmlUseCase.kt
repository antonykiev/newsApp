package com.internet.connection.news_detailed.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import javax.inject.Inject

class ContentFromHtmlUseCase @Inject constructor(
) {
    suspend operator fun invoke(
        url: String,
        contentToFind: String,
    ): String {

        val result = runCatching {
            withContext(Dispatchers.Default) {
                val document: Document = document(url)
                val element: Element = element(document, contentToFind)

                val tag = element.tagName().orEmpty()

                val result = element.parent()
                    ?.children()
                    ?.filter { it.tagName() == tag }
                    ?.joinToString(" ") { it.text() } ?: contentToFind
                return@withContext result
            }
        }
        return result.getOrDefault(contentToFind)
    }

    @Throws(Exception::class)
    private fun element(
        document: Document,
        contentToFind: String,
    ): Element {
        return document.select("*:containsOwn($contentToFind)")
            .first()
            ?: element(
                document,
                contentToFind = contentToFind
                    .split(" ")
                    .dropLast(1)
                    .joinToString(separator = " ") { it },
            )
    }

    private suspend fun document(url: String): Document {
        return withContext(Dispatchers.IO) {
            Jsoup.connect(url).get()
        }
    }
}