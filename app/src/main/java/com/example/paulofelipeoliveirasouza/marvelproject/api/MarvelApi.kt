package com.example.paulofelipeoliveirasouza.marvelproject.api

import com.example.paulofelipeoliveirasouza.marvelproject.Constants.Companion.API_KEY_PUBLIC
import com.example.paulofelipeoliveirasouza.marvelproject.Constants.Companion.HASH
import com.example.paulofelipeoliveirasouza.marvelproject.Constants.Companion.TS
import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.comics.ComicsListData
import com.example.paulofelipeoliveirasouza.marvelproject.data.marvelpersonagens.PersonagensData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi{

    @GET("characters")
    fun getMarvelApi(
            @Query("offset") offset: Int = 0,
            @Query("orderBy") orderBy: String = "name",
            @Query("limit") limit: Int = 20,
            @Query("ts") ts: String = TS,
            @Query("apikey") apikey: String = API_KEY_PUBLIC,
            @Query("hash") hash: String = HASH): Single<PersonagensData>

    @GET("characters/{id}/comics")
    fun getMarvelApiComics(
            @Path("id") id: String,
            @Query("ts") ts: String = TS,
            @Query("apikey") apikey: String = API_KEY_PUBLIC,
            @Query("hash") hash: String = HASH): Single<ComicsListData>
}