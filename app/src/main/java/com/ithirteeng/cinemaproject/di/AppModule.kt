package com.ithirteeng.cinemaproject.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.ithirteeng.cinemaproject.navigation.createCicerone
import com.ithirteeng.design.GLOBAL_ROUTER
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single { createCicerone() }
    single(named(GLOBAL_ROUTER)) { get<Cicerone<Router>>().router }
    single(named(GLOBAL_ROUTER)) { get<Cicerone<Router>>().getNavigatorHolder() }
}