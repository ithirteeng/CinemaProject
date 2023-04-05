package com.ithirteeng.errorhandler.di

import com.ithirteeng.errorhandler.presentation.ErrorDialogFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val errorModule = module {
    viewModel {
        ErrorDialogFragmentViewModel(
            router = get(),
            removeTokenFromLocalStorageUseCase = get(),
            clearProfileDataLocallyUseCase = get()
        )
    }
}