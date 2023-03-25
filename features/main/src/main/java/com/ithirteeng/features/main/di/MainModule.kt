package com.ithirteeng.features.main.di

import com.ithirteeng.features.main.presentation.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        MainFragmentViewModel(
            application = get(),
            router = get()
        )
    }
}