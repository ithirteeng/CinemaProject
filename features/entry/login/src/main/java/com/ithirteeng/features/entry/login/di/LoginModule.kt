package com.ithirteeng.features.entry.login.di

import com.ithirteeng.features.entry.login.presentation.LoginFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel {
        LoginFragmentViewModel(
            application = get(),
            router = get()
        )
    }
}