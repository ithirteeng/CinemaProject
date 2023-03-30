package com.ithirteeng.cinemaproject.di

import com.ithirteeng.cinemaproject.navigation.routers.*
import com.ithirteeng.design.GLOBAL_ROUTER
import com.ithirteeng.errorhandler.presentation.ErrorRouter
import com.ithirteeng.features.compilation.presentation.CompilationRouter
import com.ithirteeng.features.entry.login.presentation.LoginRouter
import com.ithirteeng.features.entry.registration.presentation.RegistrationRouter
import com.ithirteeng.features.main.presentation.MainRouter
import com.ithirteeng.features.mainhost.presentation.MainHostRouter
import com.ithirteeng.features.movieinfo.presentation.MovieRouter
import com.ithirteeng.features.splash.presentation.SplashRouter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val routersModule = module {

    factory<SplashRouter> { SplashRouterImpl(router = get(named(GLOBAL_ROUTER))) }
    factory<LoginRouter> { LoginRouterImpl(router = get(named(GLOBAL_ROUTER))) }
    factory<RegistrationRouter> { RegistrationRouterImpl(router = get(named(GLOBAL_ROUTER))) }
    factory<MainRouter> { MainRouterImpl(router = get(named(GLOBAL_ROUTER))) }
    factory<CompilationRouter> { CompilationRouterImpl(router = get(named(GLOBAL_ROUTER))) }
    factory<ErrorRouter> { ErrorRouterImpl(router = get(named(GLOBAL_ROUTER))) }
    factory<MovieRouter> { MovieRouterImpl(router = get(named(GLOBAL_ROUTER))) }

    factory<MainHostRouter> { MainHostRouterImpl(router = get()) }
}