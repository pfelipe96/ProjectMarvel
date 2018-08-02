package com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens

import com.google.gson.annotations.SerializedName

data class PersonagensData(
        @SerializedName("data") var data: ResultsListData? = null
)