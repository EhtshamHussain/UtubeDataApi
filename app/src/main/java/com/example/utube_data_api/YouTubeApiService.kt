package com.example.utube_data_api

import com.example.utube_data_api.UtubeDataClasses.YouTubeSearchResponse
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.experimental.ExperimentalTypeInference


interface YouTubeApiService {
    @GET("search")
    suspend fun searchVideos(
        @Query("part") part: String = "snippet",
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 10,
        @Query("type") type: String = "video",
        @Query("pageToken") pageToken: String? = null, // Pagination ke liye
        @Query("key") apiKey: String =  "AIzaSyDFbWWnS_EZQ99wp9J24yLBUXI4PrnI58o"
    ):YouTubeSearchResponse
}

object RetrofitClient{
    private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"
    val youTubeApiService:YouTubeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YouTubeApiService::class.java)
    }
}
