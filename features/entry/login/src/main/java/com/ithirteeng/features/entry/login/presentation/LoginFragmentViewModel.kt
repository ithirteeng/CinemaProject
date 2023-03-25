package com.ithirteeng.features.entry.login.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ithirteeng.customextensions.presentation.SingleEventLiveData
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.entry.login.domain.entity.LoginEntity
import com.ithirteeng.features.entry.login.domain.usecase.PostLoginDataUseCase
import com.ithirteeng.shared.network.common.NoConnectivityException
import com.ithirteeng.shared.token.domain.entity.TokenEntity
import com.ithirteeng.shared.token.domain.usecase.SaveTokenToLocalStorageUseCase
import com.ithirteeng.shared.validators.common.ValidationResult
import com.ithirteeng.shared.validators.domain.usecase.ValidateEmailUseCase
import com.ithirteeng.shared.validators.domain.usecase.ValidateTextFieldUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginFragmentViewModel(
    application: Application,
    private val router: LoginRouter,
    private val postLoginDataUseCase: PostLoginDataUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateTextFieldUseCase: ValidateTextFieldUseCase
) : AndroidViewModel(application) {
    fun navigateToRegistrationScreen() {
        router.navigateToRegistrationFragment()
    }


    fun navigateToMainHostScreen() {
        router.navigateToMainHostScreen()
    }

    private val tokenLiveData = SingleEventLiveData<TokenEntity>()
    fun postLoginData(
        loginEntity: LoginEntity,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit
    ) {
        viewModelScope.launch {
            postLoginDataUseCase(loginEntity)
                .onSuccess {
                    tokenLiveData.value = it
                    saveTokenToLocalStorageUseCase(it)
                }
                .onFailure {
                    onErrorAppearance(setupErrorCode(it))
                }
        }
    }

    fun validateEmail(email: String): ValidationResult =
        validateEmailUseCase(email)

    fun validateTextField(textField: String): ValidationResult =
        validateTextFieldUseCase(textField)

    private fun setupErrorCode(e: Throwable): ErrorModel {
        return when (e) {
            is HttpException -> ErrorModel(e.code(), e.message())
            is NoConnectivityException -> ErrorModel(e.code())
            else -> ErrorModel(0, e.message)
        }
    }

    fun getTokenLiveData(): LiveData<TokenEntity> = tokenLiveData
}