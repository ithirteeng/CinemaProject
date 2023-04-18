package com.ithirteeng.cinemaproject

import android.app.Application
import com.ithirteeng.cinemaproject.di.ciceroneModule
import com.ithirteeng.cinemaproject.di.networkModule
import com.ithirteeng.cinemaproject.di.routersModule
import com.ithirteeng.errorhandler.di.errorModule
import com.ithirteeng.features.compilation.di.compilationModule
import com.ithirteeng.features.entry.login.di.loginModule
import com.ithirteeng.features.entry.registration.di.registrationModule
import com.ithirteeng.features.episode.di.episodeModule
import com.ithirteeng.features.main.di.mainModule
import com.ithirteeng.features.mainhost.di.mainHostModule
import com.ithirteeng.features.movieinfo.di.movieModule
import com.ithirteeng.features.profile.di.profileModule
import com.ithirteeng.features.splash.di.splashModule
import com.ithirteeng.features.collections.di.collectionsModule
import com.ithirteeng.features.discussions.di.discussionsModule
import com.ithirteeng.shared.collections.di.sharedCollectionsModule
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
                ciceroneModule,
                routersModule,
                networkModule,
                validatorsModule,
                userModule,
                tokenModule,
                errorModule,
                splashModule,
                registrationModule,
                loginModule,
                mainHostModule,
                mainModule,
                movieModule,
                episodeModule,
                compilationModule,
                profileModule,
                sharedCollectionsModule,
                collectionsModule,
                discussionsModule
            )
        }
    }
}