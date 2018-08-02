package com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens

import com.google.gson.annotations.SerializedName

data class ThumbnailData(
        @SerializedName("path") var path: String,
        @SerializedName("extension") var extension: String
)