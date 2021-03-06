package com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.ui

import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.PersonagensData

interface MainActivityInterface{

    fun loadData(personagensData: PersonagensData)
    fun loadMoreData(personagensData: PersonagensData)
    fun progressBarBottom(visible: Int)
    fun progressBarCenter(visible: Int)
    fun setRefresh(visible: Boolean)
    fun snackBarOnError(message: String)
    fun isNetworkAvaliableToContext(): Boolean
    fun snackBarIsNetWorking()
}