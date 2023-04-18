package com.ithirteeng.cinemaproject.di

import com.ithirteeng.cinemaproject.navigation.routers.*
import com.ithirteeng.design.GLOBAL_ROUTER
import com.ithirteeng.errorhandler.presentation.ErrorRouter
import com.ithirteeng.features.collections.presentation.routers.*
import com.ithirteeng.features.compilation.presentation.CompilationRouter
import com.ithirteeng.features.discussions.presentation.router.DiscussionsRouter
import com.ithirteeng.features.entry.login.presentation.LoginRouter
import com.ithirteeng.features.entry.registration.presentation.RegistrationRouter
import com.ithirteeng.features.episode.presentation.EpisodeRouter
import com.ithirteeng.features.main.presentation.MainRouter
import com.ithirteeng.features.mainhost.presentation.MainHostRouter
import com.ithirteeng.features.movieinfo.presentation.MovieRouter
import com.ithirteeng.features.profile.presentation.ProfileRouter
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
    factory<ProfileRouter> { ProfileRouterImpl(router = get(named(GLOBAL_ROUTER))) }
    factory<EpisodeRouter> { EpisodeRouterImpl(router = get(named(GLOBAL_ROUTER))) }
    factory<CollectionsRouter> { CollectionsRouterImpl(router = get(named(GLOBAL_ROUTER))) }
    factory<CollectionInfoRouter> { CollectionInfoRouterImpl(router = get(named(GLOBAL_ROUTER))) }
    factory<ChooseIconRouter> { ChooseIconRouterImpl(router = get(named(GLOBAL_ROUTER))) }
    factory<DiscussionsRouter> { DiscussionsRouterImpl(router = get(named(GLOBAL_ROUTER))) }
    factory<ChangeCollectionRouter> {
        ChangeCollectionRouterImpl(router = get(named(GLOBAL_ROUTER)))
    }
    factory<CreateCollectionRouter> {
        CreateCollectionRouterImpl(router = get(named(GLOBAL_ROUTER)))
    }

    factory<MainHostRouter> { MainHostRouterImpl(router = get()) }
}