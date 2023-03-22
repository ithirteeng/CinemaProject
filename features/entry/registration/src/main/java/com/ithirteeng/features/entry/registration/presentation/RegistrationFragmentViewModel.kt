package com.ithirteeng.features.entry.registration.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.features.entry.registration.domain.entity.RegistrationEntity
import com.ithirteeng.features.entry.registration.domain.usecase.PostRegistrationDataUseCase
import com.ithirteeng.shared.network.common.NoConnectivityException
import com.ithirteeng.shared.token.domain.usecase.SaveTokenToLocalStorageUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegistrationFragmentViewModel(
    application: Application,
    private val router: RegistrationRouter,
    private val postRegistrationDataUseCase: PostRegistrationDataUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase
) : AndroidViewModel(application) {

    fun navigateToLoginScreen() {
        router.navigateToLoginScreen()
    }

    fun navigateToMainHostScreen() {
        router.navigateToMainHostScreen()
    }

    fun exit() {
        router.exit()
    }

    fun postRegistrationData(
        registrationEntity: RegistrationEntity,
        onErrorAppearance: (errorCode: Int) -> Unit
    ) {
        viewModelScope.launch {
            postRegistrationDataUseCase(registrationEntity)
                .onSuccess {
                    saveTokenToLocalStorageUseCase(it)
                }
                .onFailure {
                    setupErrorCode(it)
                }
        }
    }

    private fun setupErrorCode(e: Throwable): Int {
        return when (e) {
            is HttpException -> {
                e.code()
            }
            is NoConnectivityException -> {
                e.code()
            }
            else -> {
                0
            }
        }
    }

}