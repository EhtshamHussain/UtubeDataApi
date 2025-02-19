package com.example.utube_data_api

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.utube_data_api.Screen.YouTubeScreen
import com.example.utube_data_api.ShimmerEffect.ShimmerListItem
import com.example.utube_data_api.ui.theme.UtubeDataApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UtubeDataApiTheme {
//                Log.d("API Key Check", BuildConfig.YOUTUBE_API_KEY)
                YouTubeScreen()

            }
        }
    }
}
