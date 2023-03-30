package com.ithirteeng.cinemaproject.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.ithirteeng.cinemaproject.navigation.MainHostCustomRouter
import com.ithirteeng.cinemaproject.navigation.createCicerone
import com.ithirteeng.design.GLOBAL_ROUTER
import com.ithirteeng.design.LOCAL_ROUTER
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ciceroneModule = module {

    single(named(GLOBAL_ROUTER)) { createCicerone() }
    single(named(GLOBAL_ROUTER)) { get<Cicerone<Router>>(named(GLOBAL_ROUTER)).router }
    single(named(GLOBAL_ROUTER)) { get<Cicerone<Router>>(named(GLOBAL_ROUTER)).getNavigatorHolder() }

    single(named(LOCAL_ROUTER)) { createCicerone() }
    single(named(LOCAL_ROUTER)) { get<Cicerone<Router>>(named(LOCAL_ROUTER)).router }
    single(named(LOCAL_ROUTER)) { get<Cicerone<Router>>(named(LOCAL_ROUTER)).getNavigatorHolder() }

    single { MainHostCustomRouter(hostRouter = get(named(LOCAL_ROUTER))) }
}