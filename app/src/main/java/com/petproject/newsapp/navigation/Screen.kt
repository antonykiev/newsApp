package com.petproject.newsapp.navigation

sealed class Screen(val route: String) {

    data object NewsList : Screen(Route.NewsList.name.lowercase())

    data object NewsDetailed : Screen(Route.NewsList.name.lowercase()+ "/{articleIdKey}") {
        fun navigate(articleIdKey: Long) = Route.NewsList.name.lowercase() + "/$articleIdKey"
        const val ARTICLE_ID_KEY = "articleIdKey"
    }

}