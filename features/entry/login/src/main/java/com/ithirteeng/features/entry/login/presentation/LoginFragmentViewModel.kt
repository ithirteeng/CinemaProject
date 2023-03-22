package com.ithirteeng.features.entry.login.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.features.entry.login.domain.entity.LoginEntity
import com.ithirteeng.features.entry.login.domain.usecase.PostLoginDataUseCase
import com.ithirteeng.shared.network.common.NoConnectivityException
import com.ithirteeng.shared.token.domain.usecase.SaveTokenToLocalStorageUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginFragmentViewModel(
    application: Application,
    private val router: LoginRouter,
    private val postLoginDataUseCase: PostLoginDataUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase
) : AndroidViewModel(application) {
    fun navigateToRegistrationScreen() {
        router.navigateToRegistrationFragment()
    }

    fun navigateToMainHostScreen() {
        router.navigateToMainHostScreen()
    }

    fun exit() {
        router.exit()
    }

    fun postLoginData(
        loginEntity: LoginEntity,
        onErrorAppearance: (errorCode: Int) -> Unit
    ) {
        viewModelScope.launch {
            postLoginDataUseCase(loginEntity)
                .onSuccess {
                    saveTokenToLocalStorageUseCase(it)
                }
                .onFailure {
                    onErrorAppearance(setupErrorCode(it))
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