package com.ithirteeng.features.splash.di

import com.ithirteeng.features.splash.presentation.SplashFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
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