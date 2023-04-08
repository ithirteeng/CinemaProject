package com.ithirteeng.features.entry.registration.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ithirteeng.customextensions.presentation.SingleEventLiveData
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.entry.registration.domain.entity.RegistrationEntity
import com.ithirteeng.features.entry.registration.domain.usecase.CreateCollectionUseCase
import com.ithirteeng.features.entry.registration.domain.usecase.PostRegistrationDataUseCase
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.collections.domain.usecase.SetCreationFavouritesFlagUseCase
import com.ithirteeng.shared.network.common.NoConnectivityException
import com.ithirteeng.shared.token.domain.usecase.SaveTokenToLocalStorageUseCase
import com.ithirteeng.shared.validators.common.ValidationResult
import com.ithirteeng.shared.validators.domain.usecase.ValidateEmailUseCase
import com.ithirteeng.shared.validators.domain.usecase.ValidatePasswordsUseCase
import com.ithirteeng.shared.validators.domain.usecase.ValidateTextFieldUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegistrationFragmentViewModel(
    application: Application,
    private val router: RegistrationRouter,
    private val postRegistrationDataUseCase: PostRegistrationDataUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateTextFieldUseCase: ValidateTextFieldUseCase,
    private val validatePasswordsUseCase: ValidatePasswordsUseCase,
    private val createCollectionUseCase: CreateCollectionUseCase,
    private val setCreationFavouritesFlagUseCase: SetCreationFavouritesFlagUseCase,
) : AndroidViewModel(application) {

    fun navigateToLoginScreen() {
        router.navigateToLoginScreen()
    }

    fun navigateToMainHostScreen() {
        router.navigateToMainHostScreen()
    }

    private val requestLiveData = SingleEventLiveData<Boolean>()

    fun postRegistrationData(
        registrationEntity: RegistrationEntity,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            postRegistrationDataUseCase(registrationEntity)
                .onSuccess {
                    saveTokenToLocalStorageUseCase(it)
                    requestLiveData.value = true
                }
                .onFailure {
                    onErrorAppearance(setupErrorCode(it))
                }
        }
    }

    fun getRequestLiveData(): LiveData<Boolean> = requestLiveData

    fun validateEmail(email: String): ValidationResult =
        validateEmailUseCase(email)

    fun validateTextField(textField: String): ValidationResult =
        validateTextFieldUseCase(textField)

    fun validatePasswords(password: String, repeatedPassword: String): ValidationResult {
        val resultPassword = "$password\n/$repeatedPassword"
        return validatePasswordsUseCase(resultPassword)
    }

    private val creationCollectionLiveData = MutableLiveData<CollectionEntity>()

    fun getCollectionLiveData(): LiveData<CollectionEntity> = creationCollectionLiveData

    fun createFavouritesCollection(
        collectionName: String,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            createCollectionUseCase(CreateCollectionEntity(collectionName))
                .onSuccess {
                    creationCollectionLiveData.value = it
                }
                .onFailure {
                    onErrorAppearance(setupErrorCode(it))
                }
        }
    }


    private fun setupErrorCode(e: Throwable): ErrorModel {
        return when (e) {
            is HttpException -> ErrorModel(e.code(), e.message(), e)
            is NoConnectivityException -> ErrorModel(e.code(), null, e)
            else -> ErrorModel(0, e.message, e)
        }
    }

}