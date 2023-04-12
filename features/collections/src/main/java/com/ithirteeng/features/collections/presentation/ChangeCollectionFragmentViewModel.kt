package com.ithirteeng.features.collections.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.collections.domain.usecase.*
import com.ithirteeng.features.collections.presentation.routers.ChangeCollectionRouter
import com.ithirteeng.features.collections.presentation.utils.ChooseIconReason
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.domain.usecase.DeleteCollectionLocallyUseCase
import com.ithirteeng.shared.collections.domain.usecase.UpsertCollectionLocallyUseCase
import com.ithirteeng.shared.network.common.NoConnectivityException
import com.ithirteeng.shared.userstorage.domain.usecase.GetCurrentUserEmailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ChangeCollectionFragmentViewModel(
    private val router: ChangeCollectionRouter,
    private val createCollectionUseCase: CreateCollectionUseCase,
    private val deleteCollectionLocallyUseCase: DeleteCollectionLocallyUseCase,
    private val deleteCollectionUseCase: DeleteCollectionUseCase,
    private val getImageIdUseCase: GetImageIdUseCase,
    private val clearImageStorageUseCase: ClearImageStorageUseCase,
    private val getCollectionsListUseCase: GetCollectionsListUseCase,
    private val saveCollectionLocallyUseCase: UpsertCollectionLocallyUseCase,
    private val getCurrentUserEmailUseCase: GetCurrentUserEmailUseCase,
) : ViewModel() {

    fun exit() {
        router.exit()
    }

    fun navigateToChooseIconScreen() {
        router.navigateToChooseIconScreen(ChooseIconReason.CHANGE)
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
        oldCollectionName: String,
        collectionName: String,
        collectionsList: List<CollectionEntity>,
    ): Boolean {
        return (collectionsList.find { it.name == collectionName }) == null || collectionName == oldCollectionName
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


    private val onDeletingSuccessLiveData = MutableLiveData<Boolean>()

    fun getOnDeletingCollectionLiveData(): LiveData<Boolean> = onDeletingSuccessLiveData

    fun deleteCollection(
        localCollectionEntity: LocalCollectionEntity,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            deleteCollectionUseCase(localCollectionEntity.collectionId)
                .onSuccess {
                    launch(Dispatchers.IO) {
                        deleteCollectionLocallyUseCase(localCollectionEntity)
                        onDeletingSuccessLiveData.postValue(true)
                    }

                }
                .onFailure { onErrorAppearance(setupErrorCode(it)) }
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