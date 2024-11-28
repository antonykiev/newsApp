package com.petproject.core.mappers

import com.pet.database.entity.ArticleEntity
import com.petproject.core.data.Article

class ArticlesListToArticlesListEntity(
    private val articlesList: List<Article>,
) {
    fun articlesListEntity(): List<ArticleEntity> {
        return articlesList.map {
            ArticleToArticleEntityMapper(it).articleEntity()
        }
    }
}