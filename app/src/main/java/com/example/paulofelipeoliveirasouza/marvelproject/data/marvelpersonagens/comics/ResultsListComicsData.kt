package com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.comics

import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.comics.ComicsDataDetail
import com.google.gson.annotations.SerializedName

data class ResultsListComicsData(
        @SerializedName("results") var results: ArrayList<ComicsDataDetail>
)