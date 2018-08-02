package com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens

import com.google.gson.annotations.SerializedName

data class PersonageData(
        @SerializedName("id") var id: Int = 0,
        @SerializedName("name") var name: String = "",
        @SerializedName("description") var description: String,
        @SerializedName("thumbnail") var thumbnail: ThumbnailData? = null,
        @SerializedName("comics") var comics: ComicsData? = null
)