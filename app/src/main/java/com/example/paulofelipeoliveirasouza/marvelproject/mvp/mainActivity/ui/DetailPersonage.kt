package com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.ui

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.paulofelipeoliveirasouza.marvelproject.Constants
import com.example.paulofelipeoliveirasouza.marvelproject.MainApplication
import com.example.paulofelipeoliveirasouza.marvelproject.R
import com.example.paulofelipeoliveirasouza.marvelproject.adapter.adaptercomics.AdapterImageComics
import com.example.paulofelipeoliveirasouza.marvelproject.adapter.adapterpersonage.AdapterPersonageMarvel
import com.example.paulofelipeoliveirasouza.marvelproject.api.MarvelApi
import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.comics.ComicsListData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.detail_personage.*
import javax.inject.Inject

class DetailPersonage : AppCompatActivity() {

    @Inject
    lateinit var marvelApi: MarvelApi

    lateinit var mAdapterImageComics: AdapterImageComics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_personage)

        (application as MainApplication).applicationComponent.inject(this)

        val getIntent = intent

        val id = getIntent.getStringExtra("id")
        val name = getIntent.getStringExtra("name")
        val description = getIntent.getStringExtra("description")
        val image = getIntent.getStringExtra("image")


        Glide.with(this).load(image).into(photo_personage_detail)
        description_detail.text = description
        name_personage_detail.text = name

        id?.let {
            setProgressBarComics(View.VISIBLE)
            loadDataByApi(it)
        }
    }

    private fun loadData(comicsListData: ComicsListData) {
        setProgressBarComics(View.GONE)

        if(comicsListData.data.results.isNotEmpty()) {
            mAdapterImageComics = AdapterImageComics(comicsListData)

            recycler_view_detail?.let {
                it.apply {
                    this.visibility = View.VISIBLE
                    this.layoutManager = GridLayoutManager(this@DetailPersonage, 1, GridLayoutManager.HORIZONTAL, false)
                    this.addItemDecoration(Constants.Companion.GridSpacingItemDecoration(12, 2, true))
                    this.itemAnimator = DefaultItemAnimator()
                    this.setHasFixedSize(true)
                    this.adapter = mAdapterImageComics
                }
            }
        }else{
            showMessage(View.VISIBLE)
        }
    }

    private fun loadDataByApi(id: String) {
        marvelApi.getMarvelApiComics(id)
                .subscribeOn(IoScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            Toast.makeText(applicationContext, "Ocorreu um erro, por gentileza tente novamente", Toast.LENGTH_LONG).show()
                            setProgressBarComics(View.GONE)
                        },
                        onSuccess = {
                            loadData(it)
                        }
                )
    }

    private fun setProgressBarComics(visible: Int){
        progress_bar_comics.visibility = visible
    }

    private fun showMessage(visible: Int){
        message_not_found_image.visibility = visible
    }
}

