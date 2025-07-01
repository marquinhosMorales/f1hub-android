package com.marquinhosmorales.f1hub

import android.app.Application
import com.marquinhosmorales.f1hub.data.AppContainer
import com.marquinhosmorales.f1hub.data.DefaultAppContainer

class F1HubApplication: Application() {
    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}