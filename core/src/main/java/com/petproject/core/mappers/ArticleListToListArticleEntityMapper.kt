package com.petproject.core.mappers

import com.pet.database.entity.ArticleEntity
import com.petproject.core.data.Article

class ArticleListToListArticleEntityMapper(
    private val articleList: List<Article>
) {

    fun listArticleEntity(): List<ArticleEntity> {
        return articleList.map {
            ArticleToArticleEntityMapper(it).articleEntity()
        }
    }
}