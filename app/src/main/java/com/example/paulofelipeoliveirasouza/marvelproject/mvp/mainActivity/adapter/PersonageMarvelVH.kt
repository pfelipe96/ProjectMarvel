package com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.PersonageData
import kotlinx.android.synthetic.main.card_view_personage_marvel.view.*

class PersonageMarvelVH(private val view: View): RecyclerView.ViewHolder(view){
    private val name = view.name_personage
    private val description = view.description_personage
    private val image = view.photo_personage

    fun bind(personageData: PersonageData){
        name.text = personageData.name
        description.text = personageData.description

        Glide.with(view.context)
                .load("${personageData.thumbnail?.path}/portrait_medium.${personageData.thumbnail?.extension}")
                .into(image)
    }
}