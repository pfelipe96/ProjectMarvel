package com.example.paulofelipeoliveirasouza.marvelproject.di.module

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(application: Application){
    private val applicationInst = application

    @Provides
    @Singleton
    fun providesApplication(): Application{
        return applicationInst
    }

    @Provides
    @Singleton
    fun providesGson(): Gson{
        return GsonBuilder().setLenient().create()
    }

}