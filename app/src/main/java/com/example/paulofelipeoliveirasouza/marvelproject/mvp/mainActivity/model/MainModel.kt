package com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.model

import com.example.paulofelipeoliveirasouza.marvelproject.api.MarvelApi
import com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.presenter.MainPresenterInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainModel @Inject constructor(
        private val marvelApi: MarvelApi): MainModelInterface{

    private lateinit var reference: WeakReference<MainPresenterInterface>

    private val presenter: MainPresenterInterface?
        get() = reference.get()


    override fun attachPresenter(presenter: MainPresenterInterface) {
        reference = WeakReference(presenter)
    }


    override fun loadMarvelAPI(skip: Int) {
        val returnApi = marvelApi.getMarvelApi(skip)
                .subscribeOn(IoScheduler())
                .observeOn(AndroidSchedulers.mainThread())

        presenter?.handlerMarvel(single = returnApi)
    }
}