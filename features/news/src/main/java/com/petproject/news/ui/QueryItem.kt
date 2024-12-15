package com.petproject.news.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.petproject.core.data.Query
import com.petproject.news.ui.screenstate.SearchBarState

@Composable
fun QueryItem(
    searchBarState: SearchBarState,
    onItemClick: (query: Query) -> Unit,
) {
    LazyColumn {
        items(
            items = searchBarState.queryHistory,
            key = { it.id }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 14.dp)
                    .clickable { onItemClick(it) }
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = it.text)
            }
        }
    }
}