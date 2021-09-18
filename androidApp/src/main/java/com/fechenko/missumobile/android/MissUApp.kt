package com.fechenko.missumobile.android

import android.app.Application
import com.fechenko.missumobile.di.initKoin
import org.koin.android.ext.koin.androidContext

class MissUApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(applicationContext)
            modules()
        }
    }
}