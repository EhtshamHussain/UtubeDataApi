package com.example.utube_data_api.UtubeDataClasses

import com.google.gson.annotations.SerializedName
data class YouTubeSearchResponse(
    @SerializedName("items") val items :  List<VideoItem>,
    @SerializedName("nextPageToken") val nextPageToken: String? // Add this line
)

data class VideoItem(
    @SerializedName("id") val id: VideoId,
    @SerializedName("snippet") val snippet: Snippet
)

data class VideoId(
    @SerializedName("videoId") val videoId: String
)

data class Snippet(
    @SerializedName("title") val title: String,

    @SerializedName("thumbnails") val thumbnails: Thumbnails

)

data class Thumbnails(
    @SerializedName("high") val high: Thumbnail
)

data class Thumbnail(
    @SerializedName("url") val url: String
)