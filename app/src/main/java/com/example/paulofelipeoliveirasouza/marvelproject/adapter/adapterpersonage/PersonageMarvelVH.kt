package com.example.paulofelipeoliveirasouza.marvelproject.adapter.adapterpersonage

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.PersonageData
import com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.ui.DetailPersonage
import kotlinx.android.synthetic.main.card_view_personage_marvel.view.*

class PersonageMarvelVH(private val view: View?): RecyclerView.ViewHolder(view!!){
    private val name = view?.name_personage
    private val description = view?.description_personage
    private val image = view?.photo_personage
    private val cardPersonage = view?.card_personage

    fun bind(personageData: PersonageData){
        name?.text = personageData.name

        if(personageData.description.isNotEmpty())
            description?.text = personageData.description
        else
            description?.text = "Não há descrição"

        Glide.with(view!!.context)
                .load("${personageData.thumbnail?.path}/portrait_medium.${personageData.thumbnail?.extension}")
                .into(image!!)

        cardPersonage?.setOnClickListener{
            val intent = Intent(view.context, DetailPersonage::class.java)

            intent.putExtra("id", personageData.id.toString()
            )
            intent.putExtra("image", "${personageData.thumbnail?.path}/portrait_medium.${personageData.thumbnail?.extension}")
            intent.putExtra("description", description?.text.toString())
            intent.putExtra("name",  personageData.name)

            view.context.startActivity(intent)
        }
    }
}