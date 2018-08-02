package com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.paulofelipeoliveirasouza.marvelproject.R
import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.PersonagensData

class AdapterPersonageMarvel(private val dataSet: PersonagensData): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PersonageMarvelVH(inflater.inflate(R.layout.card_view_personage_marvel, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is PersonageMarvelVH){
            holder.bind(dataSet.data?.results!![position])
        }
    }

    override fun getItemCount(): Int = dataSet.data?.results!!.size

    fun loadMoreData(moreData: PersonagensData){
        dataSet.data?.results?.addAll(moreData.data?.results!!)
        this.notifyItemInserted(dataSet.data?.results!!.size - 1)
        this.notifyDataSetChanged()
    }

}