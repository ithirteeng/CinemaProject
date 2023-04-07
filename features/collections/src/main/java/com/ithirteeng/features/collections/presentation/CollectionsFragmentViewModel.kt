package com.ithirteeng.features.collections.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.features.collections.domain.usecase.CreateCollectionUseCase
import com.ithirteeng.features.collections.domain.usecase.GetCreationFlagUseCase
import com.ithirteeng.features.collections.domain.usecase.SetCreationFavouritesFlagUseCase
import com.ithirteeng.shared.network.common.NoConnectivityException
import com.ithirteeng.shared.userstorage.domain.usecase.GetCurrentUserEmailUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CollectionsFragmentViewModel(
    private val createCollectionUseCase: CreateCollectionUseCase,
    private val getCreationFlagUseCase: GetCreationFlagUseCase,
    private val setCreationFavouritesFlagUseCase: SetCreationFavouritesFlagUseCase,
    private val getUserEmailUseCase: GetCurrentUserEmailUseCase,
) : ViewModel() {

    fun setCreationFavouritesFlag(creationFlag: Boolean) {
        setCreationFavouritesFlagUseCase(creationFlag, getUserEmailUseCase())
    }

    fun getCreationFavouritesFlag(): Boolean =
        getCreationFlagUseCase(getUserEmailUseCase())

    private val createCollectionResultLiveData = MutableLiveData<Boolean>()

    fun getCreateCollectionResultLiveData(): LiveData<Boolean> = createCollectionResultLiveData

    fun createCollection(
        collectionName: String,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            createCollectionUseCase(CreateCollectionEntity((collectionName)))
                .onSuccess {
                    createCollectionResultLiveData.value = true
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