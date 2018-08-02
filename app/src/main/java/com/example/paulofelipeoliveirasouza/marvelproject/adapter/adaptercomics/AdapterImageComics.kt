package com.example.paulofelipeoliveirasouza.marvelproject.adapter.adaptercomics

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.paulofelipeoliveirasouza.marvelproject.R
import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.comics.ComicsListData

class AdapterImageComics(private val dataSet: ComicsListData): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImageComicVH(inflater.inflate(R.layout.image_circle_comics, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ImageComicVH){
            holder.bind(dataSet.data.results[position])
        }
    }

    override fun getItemCount(): Int = dataSet.data.results.size

}