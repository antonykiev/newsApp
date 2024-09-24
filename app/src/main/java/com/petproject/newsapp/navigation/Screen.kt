package com.petproject.newsapp.navigation

sealed class Screen(val route: String) {
    data object NewsList : Screen(Route.NewsList.name.lowercase())
}