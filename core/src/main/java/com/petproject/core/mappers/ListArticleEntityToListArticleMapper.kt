package com.petproject.core.mappers

import com.pet.database.entity.ArticleEntity
import com.petproject.core.data.Article

object ListArticleEntityToListArticleMapper {

    fun articleList(list: List<ArticleEntity>): List<Article> {
        return list.map {
            ArticleEntityToArticleMapper(it).article()
        }
    }
}