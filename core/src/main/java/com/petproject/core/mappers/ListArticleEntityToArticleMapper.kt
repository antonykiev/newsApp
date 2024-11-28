package com.petproject.core.mappers

import com.pet.database.entity.ArticleEntity
import com.petproject.core.data.Article

class ListArticleEntityToArticleMapper(
    private val list: List<ArticleEntity>,
){

    fun articleList(): List<Article> {
        return list.map {
            ArticleEntityToArticleMapper(it).article()
        }
    }
}