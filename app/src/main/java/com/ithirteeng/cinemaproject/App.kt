package com.ithirteeng.cinemaproject

import android.app.Application
import com.ithirteeng.cinemaproject.di.appModule
import com.ithirteeng.cinemaproject.di.routersModule
import com.ithirteeng.features.splash.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)

            modules(
                appModule,
                routersModule,
                splashModule
            )
        }
    }
}