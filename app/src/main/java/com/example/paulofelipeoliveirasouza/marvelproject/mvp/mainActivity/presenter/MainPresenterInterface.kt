package com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.presenter

import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.PersonagensData
import com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.ui.MainActivityInterface
import io.reactivex.Single

interface MainPresenterInterface{

    fun attachView(view: MainActivityInterface)
    fun handlerMarvel(single: Single<PersonagensData>)
    fun skipMarvelApi(skip: Int)
}