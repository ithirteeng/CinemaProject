package com.ithirteeng.errorhandler.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.shared.token.domain.usecase.RemoveTokenFromLocalStorageUseCase

class ErrorDialogFragmentViewModel(
    private val router: ErrorRouter,
    private val removeTokenFromLocalStorageUseCase: RemoveTokenFromLocalStorageUseCase,
) : ViewModel() {
    fun navigateToLoginFragment() {
        router.navigateToLoginScreen()
    }

    fun deleteToken() {
        removeTokenFromLocalStorageUseCase()
    }
}