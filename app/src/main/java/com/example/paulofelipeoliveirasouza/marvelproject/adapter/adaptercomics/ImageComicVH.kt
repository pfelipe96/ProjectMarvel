package com.example.paulofelipeoliveirasouza.marvelproject.adapter.adaptercomics

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.paulofelipeoliveirasouza.marvelproject.R
import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.comics.ComicsDataDetail
import de.hdodenhof.circleimageview.CircleImageView


class ImageComicVH(private val view: View?): RecyclerView.ViewHolder(view!!){
    private val imageCircle = view?.findViewById(R.id.image_circle) as ImageView

    fun bind(comicsDataDetail: ComicsDataDetail){

        Glide.with(view!!.context)
                .load("${comicsDataDetail.thumbnail?.path}/portrait_uncanny.${comicsDataDetail.thumbnail?.extension}")
                .into(imageCircle)

    }
}