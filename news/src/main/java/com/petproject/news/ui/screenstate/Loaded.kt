package com.petproject.news.ui.screenstate

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.petproject.news.ui.NewsItem

data class Loaded(override val args: StateArgs.LoadedArgs) : NewsScreenState(args) {
    @Composable
    override fun Handle() {
        LazyColumn {
            items(
                items = args.news,
                key = { it.title }
            ) { item ->
                NewsItem(item)
                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}