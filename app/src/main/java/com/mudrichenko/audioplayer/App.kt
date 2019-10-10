package com.mudrichenko.audioplayer

import android.app.Application
import android.content.Context
import com.mudrichenko.audioplayer.di.component.AppComponent
import com.mudrichenko.audioplayer.di.component.DaggerAppComponent
import com.mudrichenko.audioplayer.di.module.AppModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        App.appContext = applicationContext
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {

        var appContext: Context? = null
            private set

        var appComponent: AppComponent? = null
            private set

    }

}