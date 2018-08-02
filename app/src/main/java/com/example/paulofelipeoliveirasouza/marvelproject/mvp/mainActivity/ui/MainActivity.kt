package com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.paulofelipeoliveirasouza.marvelproject.MainApplication
import com.example.paulofelipeoliveirasouza.marvelproject.R
import com.example.paulofelipeoliveirasouza.marvelproject.Utils.EndlessRecyclerViewScrollListener
import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.PersonagensData
import com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.adapter.AdapterPersonageMarvel
import com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityInterface {

    private lateinit var mAdapterPersonagensData: AdapterPersonageMarvel

    private val mLinearLayoutManager = LinearLayoutManager(this@MainActivity)
    private var mSkip: Int = 0

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MainApplication).applicationComponent.inject(this)

        presenter.attachView(this)

        presenter.skipMarvelApi(mSkip)


        mLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        recycler_view.addOnScrollListener(object : EndlessRecyclerViewScrollListener(mLinearLayoutManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                 if(mSkip > 0){
                    if(totalItemsCount >= mAdapterPersonagensData.itemCount){
                        if(view.canScrollVertically(RecyclerView.FOCUS_DOWN)){
                            progressBarBottom(View.VISIBLE)
                        }
                        presenter.skipMarvelApi(mSkip)
                    }
                }else{
                    progressBarBottom(View.GONE)
                }
            }
        })
    }


    override fun loadData(personagensData: PersonagensData) {
        mAdapterPersonagensData = AdapterPersonageMarvel(personagensData)

        recycler_view?.let{
            it.apply {
                this.visibility = View.VISIBLE
                this.layoutManager = mLinearLayoutManager
                this.setHasFixedSize(true)
                this.adapter = mAdapterPersonagensData
            }
        }

        mSkip++
    }

    override fun loadMoreData(personagensData: PersonagensData) {
        mAdapterPersonagensData.loadMoreData(personagensData)
        mSkip++
        progressBarBottom(View.GONE)
    }

    override fun progressBarBottom(visible: Int) {
        progress_bar_bottom.visibility = visible
    }
}
