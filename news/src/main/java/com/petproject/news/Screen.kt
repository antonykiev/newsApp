package com.petproject.news

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.petproject.news.ui.NewsScreenState

@Composable
fun NewsList(navController: NavHostController) {
    val viewModel: NewsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    when (state) {
        is NewsScreenState.ErrorLoading -> Log.d("NewsList", state.toString())
        is NewsScreenState.Loaded -> Log.d("NewsList", state.toString())
        NewsScreenState.Loading -> Log.d("NewsList", state.toString())
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Greeting(
            name = "$state",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting("Android")
}