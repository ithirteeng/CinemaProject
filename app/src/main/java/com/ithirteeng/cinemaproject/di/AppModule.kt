package com.ithirteeng.cinemaproject.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.ithirteeng.cinemaproject.navigation.buildCicerone
import org.koin.dsl.module

val appModule = module {
    single { buildCicerone() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
}