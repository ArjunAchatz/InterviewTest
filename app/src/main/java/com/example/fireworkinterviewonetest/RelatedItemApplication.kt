package com.example.fireworkinterviewonetest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RelatedItemApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RelatedItemApplication)
            modules(appModules)
        }
    }

}