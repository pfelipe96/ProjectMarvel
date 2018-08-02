package com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.paulofelipeoliveirasouza.marvelproject.MainApplication
import com.example.paulofelipeoliveirasouza.marvelproject.R
import com.example.paulofelipeoliveirasouza.marvelproject.Utils.EndlessRecyclerViewScrollListener
import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.PersonagensData
import com.example.paulofelipeoliveirasouza.marvelproject.adapter.adapterpersonage.AdapterPersonageMarvel
import com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.os.CountDownTimer
import android.os.PersistableBundle


class MainActivity : AppCompatActivity(), MainActivityInterface {

    private var mAdapterPersonagensData: AdapterPersonageMarvel? = null

    private val mLinearLayoutManager = LinearLayoutManager(this@MainActivity)
    private var mSkip: Int = 0

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState
        )

        setContentView(R.layout.activity_main)

        (application as MainApplication).applicationComponent.inject(this)

        presenter.attachView(this)

        presenter.skipMarvelApi(mSkip)

        mLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        recycler_view.addOnScrollListener(object : EndlessRecyclerViewScrollListener(mLinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (isNetworkAvaliableToContext()) {
                    if (mSkip > 0) {
                        if (totalItemsCount >= mAdapterPersonagensData!!.itemCount) {
                            if (view.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                                progressBarBottom(View.VISIBLE)
                            }
                            presenter.skipMarvelApi(mSkip)
                        }
                    } else {
                        progressBarBottom(View.GONE)
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        snackBarIsNetWorking()
                    }
                }
            }
        })

        swipe_refresh.setOnRefreshListener {
            mAdapterPersonagensData?.swipeRefresh()
            mSkip = 0
            presenter.skipMarvelApi(mSkip)
        }

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && floating.isShown) {
                    floating.hide()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    floating.show()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        floating.setOnClickListener{
            animateScrollUp()
        }
    }

    override fun loadData(personagensData: PersonagensData) {
        mAdapterPersonagensData = AdapterPersonageMarvel(personagensData)

        recycler_view?.let {
            it.apply {
                this.visibility = View.VISIBLE
                this.layoutManager = mLinearLayoutManager
                this.setHasFixedSize(true)
                this.adapter = mAdapterPersonagensData
            }
        }

        mSkip = personagensData.data?.offset!!.plus(20)
    }

    override fun loadMoreData(personagensData: PersonagensData) {
        mAdapterPersonagensData?.loadMoreData(personagensData)
        progressBarBottom(View.GONE)

        mSkip = personagensData.data?.offset!!.plus(20)
    }

    override fun progressBarBottom(visible: Int) {
        progress_bar_bottom.visibility = visible
    }

    override fun progressBarCenter(visible: Int) {
        progress_bar_center.visibility = visible
    }

    override fun isNetworkAvaliableToContext(): Boolean {
        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo

        var isAvailable = false

        if (networkInfo != null && networkInfo.isConnected) {
            isAvailable = true
        }
        return isAvailable
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun snackBarOnError(message: String) {
        val snackBar = Snackbar
                .make(main_view, message, 10000)
                .setActionTextColor(getColor(R.color.colorAccent))
                .setAction("Ok") {
                }

        val sbView = snackBar.view
        val textView = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(Color.WHITE)
        snackBar.show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun snackBarIsNetWorking() {
        val snackBar = Snackbar
                .make(main_view, getString(R.string.dialog_title_not_working_networking), 20000)
                .setActionTextColor(getColor(R.color.colorAccent))
                .setAction("Ok") {
                    val intent = intent
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    finish()
                    startActivity(intent)
                }

        val sbView = snackBar.view
        val textView = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(Color.WHITE)
        snackBar.show()
    }

    override fun setRefresh(visible: Boolean) {
        swipe_refresh.isRefreshing = visible
    }

    private fun animateScrollUp() {
        val mItemCount = recycler_view.adapter?.itemCount!!
        if (mItemCount <= 25) {
            recycler_view.smoothScrollToPosition(0)
        } else {
            object : CountDownTimer(500, 500) {
                override fun onTick(millisUntilFinished: Long) {
                    recycler_view.smoothScrollToPosition(0)
                }

                override fun onFinish() {
                    recycler_view.scrollToPosition(0)
                }
            }.start()
        }
    }

}
