package com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.comics

import com.google.gson.annotations.SerializedName

data class ComicsListData(
        @SerializedName("data") var data: ResultsListComicsData
)