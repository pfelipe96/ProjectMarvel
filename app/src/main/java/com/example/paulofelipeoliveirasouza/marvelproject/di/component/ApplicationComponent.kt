package com.example.paulofelipeoliveirasouza.marvelproject.di.component

import com.example.paulofelipeoliveirasouza.marvelproject.MainApplication
import com.example.paulofelipeoliveirasouza.marvelproject.di.module.ApiModule
import com.example.paulofelipeoliveirasouza.marvelproject.di.module.ApplicationModule
import com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.ui.DetailPersonage
import com.example.paulofelipeoliveirasouza.marvelproject.mvp.mainActivity.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, ApplicationModule::class])
interface ApplicationComponent{

    fun inject(mainApplication: MainApplication)
    fun inject(mainActivity: MainActivity)
    fun inject(detailPersonage: DetailPersonage)
}