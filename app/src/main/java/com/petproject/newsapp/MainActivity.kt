package com.petproject.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.internet.connection.news_detailed.ui.NewsDetailedScreen
import com.petproject.news.ui.NewsListScreen
import com.petproject.newsapp.navigation.Screen
import com.petproject.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        RootNavHost()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RootNavHost() {
    SharedTransitionLayout {
        val sharedTransitionScope = this
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.NewsList.route
        ) {
            composable(
                route = Screen.NewsList.route
            ) {
                NewsListScreen(
                    onNewsClick = { articleId ->
                        navController.navigate(Screen.NewsDetailed.navigate(articleId))
                    },
                    animatedVisibilityScope = this,
                )
            }
            composable(
                route = Screen.NewsDetailed.route,
                arguments = listOf(
                    navArgument(Screen.NewsDetailed.ARTICLE_ID_KEY) { type = NavType.LongType }
                )
            ) { backStackEntry ->
                val articleId =
                    backStackEntry.arguments?.getLong(Screen.NewsDetailed.ARTICLE_ID_KEY) ?: -1
                NewsDetailedScreen(
                    articleId = articleId,
                    onBackClick = { navController.popBackStack() },
                    animatedVisibilityScope = this,
                )
            }
        }
    }

}
