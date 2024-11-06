package com.petproject.mappers

import com.petproject.data.Everything
import com.petproject.server.response.EverythingResponse

class EverythingResponseToEverythingMapper(
    private val response: EverythingResponse,
) {
    fun everything(): Everything {
        return Everything(
            status = response.status,
            totalResults = response.totalResults,
            articles = response.articles.map {
                ArticleResponseToArticleMapper(
                    response = it,
                ).article()
            }
        )
    }
}