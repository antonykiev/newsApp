package com.petproject.core.mappers

import com.pet.database.entity.ArticleEntity
import com.petproject.core.data.Article

class ArticleListToListArticleEntityMapper(
    private val articleList: List<Article>,
    private val keyword: String
) {

    fun listArticleEntity(): List<ArticleEntity> {
        return articleList.map {
            ArticleToArticleEntityMapper(
                article = it,
                keyword = keyword
            ).articleEntity()
        }
    }
}