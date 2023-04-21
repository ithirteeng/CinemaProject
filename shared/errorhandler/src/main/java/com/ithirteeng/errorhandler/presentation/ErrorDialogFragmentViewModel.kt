package com.ithirteeng.errorhandler.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.shared.token.domain.usecase.RemoveTokenFromLocalStorageUseCase
import com.ithirteeng.shared.userstorage.domain.usecase.ClearProfileDataLocallyUseCase

class ErrorDialogFragmentViewModel(
    private val router: ErrorRouter,
    private val removeTokenFromLocalStorageUseCase: RemoveTokenFromLocalStorageUseCase,
    private val clearProfileDataLocallyUseCase: ClearProfileDataLocallyUseCase,
) : ViewModel() {
    fun navigateToLoginFragment() {
        router.navigateToLoginScreen()
    }

    fun removeUserFullData() {
        removeTokenFromLocalStorageUseCase()
        clearProfileDataLocallyUseCase
    }
}