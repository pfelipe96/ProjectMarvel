package com.example.paulofelipeoliveirasouza.marvelproject.di.module

import com.example.paulofelipeoliveirasouza.marvelproject.api.MarvelApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module(includes = [ApplicationModule::class])
class ApiModule{

    @Provides
    @Named("RetrofitMarvel")
    fun provideMarvel(gson: Gson): Retrofit{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://gateway.marvel.com/v1/public/")
                .build()
    }

    @Provides
    fun provideMarvelService(@Named("RetrofitMarvel") marvel: Retrofit): MarvelApi{
        return marvel.create(MarvelApi::class.java)
    }

}