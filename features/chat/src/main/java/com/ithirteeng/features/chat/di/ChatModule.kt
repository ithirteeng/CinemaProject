package com.ithirteeng.features.chat.di

import com.ithirteeng.features.chat.presentation.ChatFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val chatModule = module {
    viewModel {
        ChatFragmentViewModel(
            router = get()
        )
    }
}