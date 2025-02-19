package com.example.utube_data_api.Screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.utube_data_api.UtubeDataClasses.VideoItem
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.items
import com.example.utube_data_api.ShimmerEffect.ShimmerListItem
import com.example.utube_data_api.ViewModel.YouTubeViewModel
@Composable
fun YouTubeScreen(viewModel: YouTubeViewModel = viewModel()) {
    val items by viewModel.items
    val isLoading by viewModel.isLoading
    var query by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    // Automatic pagination when scrolling
    if (!isLoading) {
        LaunchedEffect(listState) {
            snapshotFlow { listState.layoutInfo.visibleItemsInfo }
                .collect { visibleItems ->
                    if (visibleItems.isNotEmpty()) {
                        val lastIndex = visibleItems.last().index
                        val totalItems = items.size

                        // Load more when 2 items away from the end
                        if (lastIndex >= totalItems - 2) {
                            viewModel.loadMoreItems()
                        }
                    }
                }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        TextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text("Search YouTube") }
        )

        // Search Button
        Button(
            onClick = { viewModel.searchItems(query) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Search")
        }

        // Loading Indicator
        if (isLoading) {
            ShimmerListItem()
            CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
        }

        // Video List
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(items) { item ->
                VideoItemView(item)
            }
        }
    }
}

@Composable
fun VideoItemView(video: VideoItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = video.snippet.thumbnails.high.url,
            contentDescription = "Thumbnail",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = video.snippet.title,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
