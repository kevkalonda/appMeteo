package com.example.meteo.ui

import android.app.Application
import com.example.meteo.ui.meteo.appModule
import com.example.meteo.ui.meteo.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class WeatherApplication: Application() {
        override fun onCreate() {
            super.onCreate()
            startKoin {
                androidContext(this@WeatherApplication)
                modules(appModule)
                modules(commonModule)
            }
        }
}