package com.ithirteeng.features.entry.registration.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ithirteeng.customextensions.presentation.SingleEventLiveData
import com.ithirteeng.errorhandler.domain.ErrorModel
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

    private val requestLiveData = SingleEventLiveData<Boolean>()

    fun postRegistrationData(
        registrationEntity: RegistrationEntity,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit
    ) {
        viewModelScope.launch {
            postRegistrationDataUseCase(registrationEntity)
                .onSuccess {
                    requestLiveData.value = true
                    saveTokenToLocalStorageUseCase(it)
                }
                .onFailure {
                    onErrorAppearance(setupErrorCode(it))
                }
        }
    }

    fun getRequestLiveData(): LiveData<Boolean> = requestLiveData

    private fun setupErrorCode(e: Throwable): ErrorModel {
        return when (e) {
            is HttpException -> ErrorModel(e.code(), e.message())
            is NoConnectivityException -> ErrorModel(e.code())
            else -> ErrorModel(0, e.message)
        }
    }

}