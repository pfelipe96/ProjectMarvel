package com.example.paulofelipeoliveirasouza.marvelproject

import android.app.Application
import com.example.paulofelipeoliveirasouza.marvelproject.di.component.ApplicationComponent
import com.example.paulofelipeoliveirasouza.marvelproject.di.component.DaggerApplicationComponent
import com.example.paulofelipeoliveirasouza.marvelproject.di.module.ApiModule
import com.example.paulofelipeoliveirasouza.marvelproject.di.module.ApplicationModule


class MainApplication: Application(){

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .apiModule(ApiModule())
                .build()

        applicationComponent.inject(this)
    }
}