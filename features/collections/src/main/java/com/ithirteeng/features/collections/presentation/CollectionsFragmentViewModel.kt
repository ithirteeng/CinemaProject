package com.ithirteeng.features.collections.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.collections.domain.usecase.CreateCollectionUseCase
import com.ithirteeng.features.collections.domain.usecase.GetCreationFlagUseCase
import com.ithirteeng.features.collections.domain.usecase.SetCreationFavouritesFlagUseCase
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.domain.usecase.GetCollectionImageIdUseCase
import com.ithirteeng.shared.collections.domain.usecase.SaveCollectionLocallyUseCase
import com.ithirteeng.shared.network.common.NoConnectivityException
import com.ithirteeng.shared.userstorage.domain.usecase.GetCurrentUserEmailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CollectionsFragmentViewModel(
    private val createCollectionUseCase: CreateCollectionUseCase,
    private val getCreationFlagUseCase: GetCreationFlagUseCase,
    private val setCreationFavouritesFlagUseCase: SetCreationFavouritesFlagUseCase,
    private val getUserEmailUseCase: GetCurrentUserEmailUseCase,
    private val getCollectionImageIdUseCase: GetCollectionImageIdUseCase,
    private val saveCollectionLocallyUseCase: SaveCollectionLocallyUseCase,
) : ViewModel() {

    fun setCreationFavouritesFlag(creationFlag: Boolean) {
        setCreationFavouritesFlagUseCase(creationFlag, getUserEmailUseCase())
    }

    fun saveCollectionLocally(localCollectionEntity: LocalCollectionEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            saveCollectionLocallyUseCase(localCollectionEntity)
        }
    }

    fun getCreationFavouritesFlag(): Boolean =
        getCreationFlagUseCase(getUserEmailUseCase())

    private val createCollectionResultLiveData = MutableLiveData<CollectionEntity>()

    fun getCreateCollectionResultLiveData(): LiveData<CollectionEntity> =
        createCollectionResultLiveData

    fun createCollection(
        collectionName: String,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            createCollectionUseCase(CreateCollectionEntity((collectionName)))
                .onSuccess {
                    createCollectionResultLiveData.value = it
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