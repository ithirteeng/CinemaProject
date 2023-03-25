package com.ithirteeng.features.splash.di

import androidx.navigation.NavController
import com.ithirteeng.features.splash.presentation.SplashFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val SPLASH = "SPLASH"

val splashModule = module {
    single(named(SPLASH)) { NavController(context = get()) }

    viewModel {
        SplashFragmentViewModel(
            application = get(),
            router = get(),
            checkIfUserEnteredAppUseCase = get(),
            setUserEntryFlagUseCase = get(),
            checkTokenExistenceUseCase = get()
        )
    }
}