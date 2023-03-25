package com.ithirteeng.features.mainhost.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.ithirteeng.design.LOCAL_ROUTER
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainHostModule = module {

    single(named(LOCAL_ROUTER)) { get<Cicerone<Router>>().router }
    single(named(LOCAL_ROUTER)) { get<Cicerone<Router>>().getNavigatorHolder() }



}