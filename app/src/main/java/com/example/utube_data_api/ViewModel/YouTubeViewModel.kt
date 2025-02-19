package com.example.utube_data_api.ViewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utube_data_api.RetrofitClient
import com.example.utube_data_api.UtubeDataClasses.VideoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class YouTubeViewModel : ViewModel() {
    private val _items = mutableStateOf<List<VideoItem>>(emptyList())
    val items: State<List<VideoItem>> = _items

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private var nextPageToken: String? = null
    private var currentQuery: String? = null

    fun searchItems(query: String) {
        if (query.isBlank()) return

        // New search: reset state
        if (query != currentQuery) {
            _items.value = emptyList()
            nextPageToken = null
        }

        currentQuery = query
        fetchItems(query, isNewSearch = query != currentQuery)
    }

    private fun fetchItems(query: String, isNewSearch: Boolean) {
        if (_isLoading.value) return // Prevent duplicate calls

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitClient.youTubeApiService.searchVideos(
                    query = query,
                    pageToken = nextPageToken
                )

                if (response.items.isNotEmpty()) {
                    _items.value = if (isNewSearch) {
                        response.items
                    } else {
                        _items.value + response.items
                    }
                    nextPageToken = response.nextPageToken
                }
            } catch (e: Exception) {
                Log.e("API Error", e.toString())
            }
            _isLoading.value = false
        }
    }

    fun loadMoreItems() {
        currentQuery?.let { query ->
            if (nextPageToken != null && !_isLoading.value) {
                fetchItems(query, isNewSearch = false)
            }
        }
    }
}