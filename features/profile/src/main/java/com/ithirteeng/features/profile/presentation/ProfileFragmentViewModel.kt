package com.ithirteeng.features.profile.presentation

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ithirteeng.customextensions.presentation.SingleEventLiveData
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.profile.data.mapper.toUserEntity
import com.ithirteeng.features.profile.domain.usecase.GetProfileDataUseCase
import com.ithirteeng.features.profile.domain.usecase.UploadUserAvatarUseCase
import com.ithirteeng.shared.network.common.NoConnectivityException
import com.ithirteeng.shared.token.domain.usecase.RemoveTokenFromLocalStorageUseCase
import com.ithirteeng.shared.userstorage.domain.entity.UserEntity
import com.ithirteeng.shared.userstorage.domain.usecase.ClearProfileDataLocallyUseCase
import com.ithirteeng.shared.userstorage.domain.usecase.GetLocalUserDataUseCase
import com.ithirteeng.shared.userstorage.domain.usecase.SaveUserDataLocallyUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.ByteArrayOutputStream

class ProfileFragmentViewModel(
    application: Application,
    private val getProfileDataUseCase: GetProfileDataUseCase,
    private val uploadUserAvatarUseCase: UploadUserAvatarUseCase,
    private val getLocalUserDataUseCase: GetLocalUserDataUseCase,
    private val saveUserDataLocallyUseCase: SaveUserDataLocallyUseCase,
    private val clearProfileDataLocallyUseCase: ClearProfileDataLocallyUseCase,
    private val removeTokenFromLocalStorageUseCase: RemoveTokenFromLocalStorageUseCase,
    private val router: ProfileRouter,
) : AndroidViewModel(application) {

    private fun navigateToLoginScreen() =
        router.navigateToLoginScreen()

    private fun clearLocalUserData() {
        clearProfileDataLocallyUseCase()
        removeTokenFromLocalStorageUseCase()
    }

    fun logoutUser() {
        clearLocalUserData()
        navigateToLoginScreen()
    }

    private fun getLocalUserData(): UserEntity? =
        getLocalUserDataUseCase()

    fun uploadUserAvatar(bitmap: Bitmap, onErrorAppearance: (errorModel: ErrorModel) -> Unit) {
        viewModelScope.launch {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream)

            uploadUserAvatarUseCase(byteArrayOutputStream.toByteArray())
                .onSuccess {
                    clearProfileDataLocallyUseCase()
                    getProfileData { onErrorAppearance(it) }
                }
                .onFailure { onErrorAppearance(setupErrorCode(it)) }
        }
    }

    private val profileLiveData = SingleEventLiveData<UserEntity>()

    fun getProfileLiveData(): LiveData<UserEntity> = profileLiveData

    fun getProfileData(onErrorAppearance: (errorModel: ErrorModel) -> Unit) {
        if (getLocalUserData() == null) {
            viewModelScope.launch {
                getProfileDataUseCase()
                    .onSuccess {
                        saveUserDataLocallyUseCase(it.toUserEntity())
                        profileLiveData.value = it.toUserEntity()
                    }
                    .onFailure {
                        onErrorAppearance(setupErrorCode(it))
                    }
            }
        } else {
            profileLiveData.value = getLocalUserData()
        }
    }

    fun saveUserdataLocally(userEntity: UserEntity) =
        saveUserDataLocallyUseCase(userEntity)

    private fun setupErrorCode(e: Throwable): ErrorModel {
        return when (e) {
            is HttpException -> ErrorModel(e.code(), e.message(), e)
            is NoConnectivityException -> ErrorModel(e.code(), null, e)
            else -> ErrorModel(0, e.message, e)
        }
    }

}