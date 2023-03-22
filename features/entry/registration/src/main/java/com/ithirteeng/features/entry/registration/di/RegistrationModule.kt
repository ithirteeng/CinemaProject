package com.ithirteeng.features.entry.registration.di

import com.ithirteeng.features.entry.registration.presentation.RegistrationFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registrationModule = module {
    viewModel {
        RegistrationFragmentViewModel(
            application = get(),
            router = get()
        )
    }
}