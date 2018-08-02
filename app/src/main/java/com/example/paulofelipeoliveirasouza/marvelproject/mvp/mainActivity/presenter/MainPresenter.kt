package com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.presenter

import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.PersonagensData
import com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.model.MainModel
import com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.ui.MainActivityInterface
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainPresenter @Inject constructor(
        private val mainModel: MainModel): MainPresenterInterface{

    private lateinit var reference: WeakReference<MainActivityInterface>

    init {
        mainModel.attachPresenter(this)
    }

    private val view: MainActivityInterface?
        get() = reference.get()

    override fun attachView(view: MainActivityInterface) {
        reference = WeakReference(view)
    }

    override fun handlerMarvel(single: Single<PersonagensData>) {
        single.subscribeBy(
                onError = {
                    it
                },
                onSuccess = {
                    if(it.data?.offset == 0) {
                        view?.loadData(it)
                    }else{
                        view?.loadMoreData(it)
                    }
                }
        )
    }

    override fun skipMarvelApi(skip: Int) {
        mainModel.loadMarvelAPI(skip = skip)
    }


}