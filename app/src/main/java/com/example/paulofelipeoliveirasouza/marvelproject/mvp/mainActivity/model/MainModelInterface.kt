package com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.model

import com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.presenter.MainPresenterInterface

interface MainModelInterface{

    fun attachPresenter(presenter: MainPresenterInterface)
    fun loadMarvelAPI(skip: Int)
}