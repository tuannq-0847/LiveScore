package com.sun.livescore

import android.app.Application
import com.sun.livescore.di.appModule
import com.sun.livescore.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule, networkModule)
        }
    }
}
