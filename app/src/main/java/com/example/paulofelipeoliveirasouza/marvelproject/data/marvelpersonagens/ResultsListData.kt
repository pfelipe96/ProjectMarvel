package com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens

import com.google.gson.annotations.SerializedName

data class ResultsListData(
        @SerializedName("results") val results: ArrayList<PersonageData>? = null,
        @SerializedName("offset") val offset: Int = 0
)