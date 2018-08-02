package com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.comics

import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.ThumbnailData
import com.google.gson.annotations.SerializedName

class ComicsDataDetail(
        @SerializedName("title") var title: String,
        @SerializedName("thumbnail") var thumbnail: ThumbnailData? = null
)