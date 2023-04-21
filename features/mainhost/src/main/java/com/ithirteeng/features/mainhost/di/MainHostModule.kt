package com.ithirteeng.features.mainhost.di

import com.ithirteeng.features.mainhost.presentation.MainHostFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainHostModule = module {
    viewModel {
        MainHostFragmentViewModel(
            application = get(),
            router = get()
        )
    }

}