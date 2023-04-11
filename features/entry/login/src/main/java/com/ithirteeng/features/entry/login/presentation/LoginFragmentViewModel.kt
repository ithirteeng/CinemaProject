package com.ithirteeng.features.entry.login.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ithirteeng.component.design.R.string
import com.ithirteeng.customextensions.presentation.SingleEventLiveData
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.entry.login.domain.entity.LoginEntity
import com.ithirteeng.features.entry.login.domain.usecase.GetCollectionsListUseCase
import com.ithirteeng.features.entry.login.domain.usecase.PostLoginDataUseCase
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.domain.usecase.UpsertCollectionLocallyUseCase
import com.ithirteeng.shared.collections.presentation.collectionsIconsIds
import com.ithirteeng.shared.network.common.NoConnectivityException
import com.ithirteeng.shared.token.domain.entity.TokenEntity
import com.ithirteeng.shared.token.domain.usecase.SaveTokenToLocalStorageUseCase
import com.ithirteeng.shared.userstorage.domain.usecase.SetCurrentUserEmailUseCase
import com.ithirteeng.shared.validators.common.ValidationResult
import com.ithirteeng.shared.validators.domain.usecase.ValidateEmailUseCase
import com.ithirteeng.shared.validators.domain.usecase.ValidateTextFieldUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginFragmentViewModel(
    private val application: Application,
    private val router: LoginRouter,
    private val postLoginDataUseCase: PostLoginDataUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateTextFieldUseCase: ValidateTextFieldUseCase,
    private val setCurrentUserEmailUseCase: SetCurrentUserEmailUseCase,
    private val upsertCollectionLocallyUseCase: UpsertCollectionLocallyUseCase,
    private val getCollectionsListUseCase: GetCollectionsListUseCase,
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
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            postLoginDataUseCase(loginEntity)
                .onSuccess {
                    saveTokenToLocalStorageUseCase(it)
                    setCurrentUserEmailUseCase(loginEntity.email)
                    tokenLiveData.value = it
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

    private val favouritesCollectionLiveData = MutableLiveData<CollectionEntity>()

    fun getFavouritesCollectionLiveData(): LiveData<CollectionEntity> = favouritesCollectionLiveData

    fun makeGetFavouritesCollectionRequest(
        userEmail: String,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            getCollectionsListUseCase()
                .onSuccess {
                    for (collection in it) {
                        saveCollectionLocally(
                            collection,
                            userEmail,
                            checkIfCollectionIsFavourite(collection)
                        )
                    }
                    favouritesCollectionLiveData.postValue(it[0])
                }
                .onFailure { onErrorAppearance(setupErrorCode(it)) }
        }
    }

    private fun checkIfCollectionIsFavourite(collectionEntity: CollectionEntity): Boolean {
        return collectionEntity.name == application.getString(string.favourites_collection)
    }

    private fun saveCollectionLocally(
        collectionEntity: CollectionEntity,
        email: String,
        isCollectionFavourite: Boolean,
    ) {
        upsertCollectionLocallyUseCase(
            LocalCollectionEntity(
                collectionId = collectionEntity.id,
                collectionName = collectionEntity.name,
                collectionImageId = collectionsIconsIds[0],
                isFavourite = isCollectionFavourite,
                userEmail = email
            )
        )
    }

    private fun setupErrorCode(e: Throwable): ErrorModel {
        return when (e) {
            is HttpException -> ErrorModel(e.code(), e.message(), e)
            is NoConnectivityException -> ErrorModel(e.code(), null, e)
            else -> ErrorModel(0, e.message, e)
        }
    }

    fun getTokenLiveData(): LiveData<TokenEntity> = tokenLiveData
}