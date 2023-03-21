package com.ithirteeng.cinemaproject.di

import com.ithirteeng.cinemaproject.navigation.routers.SplashRouterImpl
import com.ithirteeng.features.splash.presentation.SplashRouter
import org.koin.dsl.module

val routersModule = module {
    factory<SplashRouter> { SplashRouterImpl(router = get()) }
}