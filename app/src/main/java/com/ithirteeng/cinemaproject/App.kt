package com.ithirteeng.cinemaproject

import android.app.Application
import com.ithirteeng.cinemaproject.di.appModule
import com.ithirteeng.cinemaproject.di.networkModule
import com.ithirteeng.cinemaproject.di.routersModule
import com.ithirteeng.features.entry.login.di.loginModule
import com.ithirteeng.features.entry.registration.di.registrationModule
import com.ithirteeng.features.mainhost.di.mainHostModule
import com.ithirteeng.features.splash.di.splashModule
import com.ithirteeng.shared.token.di.tokenModule
import com.ithirteeng.shared.userstorage.di.userModule
import com.ithirteeng.shared.validators.di.validatorsModule
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
                networkModule,
                validatorsModule,
                userModule,
                tokenModule,
                splashModule,
                registrationModule,
                loginModule,
                mainHostModule
            )
        }
    }
}