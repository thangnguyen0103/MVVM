package com.thangnguyen.demomvvm

import android.app.Application
import com.thangnguyen.demomvvm.di.appModule
import com.thangnguyen.demomvvm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@MyApp)
            // use modules
            modules(appModule + viewModelModule)
        }
    }
}