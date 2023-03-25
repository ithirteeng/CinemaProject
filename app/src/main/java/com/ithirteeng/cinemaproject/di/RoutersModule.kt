package com.ithirteeng.cinemaproject.di

import com.ithirteeng.cinemaproject.navigation.routers.LoginRouterImpl
import com.ithirteeng.cinemaproject.navigation.routers.MainHostRouterImpl
import com.ithirteeng.cinemaproject.navigation.routers.RegistrationRouterImpl
import com.ithirteeng.cinemaproject.navigation.routers.SplashRouterImpl
import com.ithirteeng.design.GLOBAL_ROUTER
import com.ithirteeng.design.LOCAL_ROUTER
import com.ithirteeng.features.entry.login.presentation.LoginRouter
import com.ithirteeng.features.entry.registration.presentation.RegistrationRouter
import com.ithirteeng.features.mainhost.presentation.MainHostRouter
import com.ithirteeng.features.splash.presentation.SplashRouter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val routersModule = module {

    factory<SplashRouter> { SplashRouterImpl(router = get(named(GLOBAL_ROUTER))) }
    factory<LoginRouter> { LoginRouterImpl(router = get(named(GLOBAL_ROUTER))) }
    factory<RegistrationRouter> { RegistrationRouterImpl(router = get(named(GLOBAL_ROUTER))) }

    factory<MainHostRouter> { MainHostRouterImpl(router = get(named(LOCAL_ROUTER))) }
}