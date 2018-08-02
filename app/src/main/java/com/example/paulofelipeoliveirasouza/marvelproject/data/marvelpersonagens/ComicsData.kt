package com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens

import com.google.gson.annotations.SerializedName

data class ComicsData(
        @SerializedName("collectionURI") var collectionURI: String = ""
)