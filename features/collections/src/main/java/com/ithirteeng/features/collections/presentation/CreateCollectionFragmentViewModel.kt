package com.ithirteeng.features.collections.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.collections.domain.usecase.ClearImageStorageUseCase
import com.ithirteeng.features.collections.domain.usecase.CreateCollectionUseCase
import com.ithirteeng.features.collections.domain.usecase.GetCollectionsListUseCase
import com.ithirteeng.features.collections.domain.usecase.GetImageIdUseCase
import com.ithirteeng.features.collections.presentation.routers.CreateCollectionRouter
import com.ithirteeng.features.collections.presentation.utils.ChooseIconReason
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.domain.usecase.UpsertCollectionLocallyUseCase
import com.ithirteeng.shared.network.common.NoConnectivityException
import com.ithirteeng.shared.userstorage.domain.usecase.GetCurrentUserEmailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CreateCollectionFragmentViewModel(
    private val createCollectionUseCase: CreateCollectionUseCase,
    private val router: CreateCollectionRouter,
    private val getImageIdUseCase: GetImageIdUseCase,
    private val clearImageStorageUseCase: ClearImageStorageUseCase,
    private val getCollectionsListUseCase: GetCollectionsListUseCase,
    private val saveCollectionLocallyUseCase: UpsertCollectionLocallyUseCase,
    private val getCurrentUserEmailUseCase: GetCurrentUserEmailUseCase,
) : ViewModel() {

    fun navigateToChooseIconScreen() {
        router.navigateToChooseIconScreen(ChooseIconReason.CREATE)
    }

    fun exit() {
        router.exit()
    }

    fun getChosenIconId(): Int {
        val iconId = getImageIdUseCase()
        clearImageStorageUseCase()
        return iconId
    }

    private val collectionsListLiveData = MutableLiveData<List<CollectionEntity>>()

    fun getCollectionListLiveData(): LiveData<List<CollectionEntity>> = collectionsListLiveData

    fun makeGetCollectionListRequest(onErrorAppearance: (errorModel: ErrorModel) -> Unit) {

        viewModelScope.launch {
            getCollectionsListUseCase()
                .onSuccess {
                    collectionsListLiveData.value = it
                }
                .onFailure { onErrorAppearance(setupErrorCode(it)) }
        }
    }

    fun checkOnCorrectName(
        collectionName: String,
        collectionsList: List<CollectionEntity>,
    ): Boolean {
        return (collectionsList.find { it.name == collectionName }) == null
    }

    private val onSavingSuccessLiveData = MutableLiveData<Boolean>()

    fun getOnSavingCollectionLiveData(): LiveData<Boolean> = onSavingSuccessLiveData

    fun saveCollectionLocally(collectionEntity: CollectionEntity, imageId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            saveCollectionLocallyUseCase(
                LocalCollectionEntity(
                    collectionId = collectionEntity.id,
                    collectionName = collectionEntity.name,
                    collectionImageId = imageId,
                    isFavourite = false,
                    userEmail = getCurrentUserEmailUseCase()
                )
            )
            onSavingSuccessLiveData.postValue(true)
        }
    }

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