package com.ithirteeng.cinemaproject.di

import com.ithirteeng.cinemaproject.navigation.routers.LoginRouterImpl
import com.ithirteeng.cinemaproject.navigation.routers.RegistrationRouterImpl
import com.ithirteeng.cinemaproject.navigation.routers.SplashRouterImpl
import com.ithirteeng.features.entry.login.presentation.LoginRouter
import com.ithirteeng.features.entry.registration.presentation.RegistrationRouter
import com.ithirteeng.features.splash.presentation.SplashRouter
import org.koin.dsl.module

val routersModule = module {
    factory<SplashRouter> { SplashRouterImpl(router = get()) }
    factory<LoginRouter> { LoginRouterImpl(router = get()) }
    factory<RegistrationRouter> { RegistrationRouterImpl(router = get()) }
}