package com.ithirteeng.features.collections.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.collections.domain.usecase.CreateCollectionUseCase
import com.ithirteeng.features.collections.domain.usecase.GetCollectionsListUseCase
import com.ithirteeng.features.collections.presentation.routers.CollectionsRouter
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.domain.usecase.GetCollectionByIdUseCase
import com.ithirteeng.shared.collections.domain.usecase.UpsertCollectionLocallyUseCase
import com.ithirteeng.shared.collections.presentation.collectionsIconsIds
import com.ithirteeng.shared.network.common.NoConnectivityException
import com.ithirteeng.shared.userstorage.domain.usecase.GetCurrentUserEmailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CollectionsFragmentViewModel(
    application: Application,
    private val createCollectionUseCase: CreateCollectionUseCase,
    private val getCollectionByIdUseCase: GetCollectionByIdUseCase,
    private val upsertCollectionLocallyUseCase: UpsertCollectionLocallyUseCase,
    private val getCollectionsListUseCase: GetCollectionsListUseCase,
    private val getCurrentUserEmailUseCase: GetCurrentUserEmailUseCase,
    private val router: CollectionsRouter,
) : AndroidViewModel(application) {

    fun exit() {
        router.exit()
    }

    fun navigateToAddCollectionScreen() {
        router.navigateToAddCollectionScreen()
    }

    fun navigateToCollectionInfoScreen(collectionId: String, collectionName: String) {
        router.navigateToCollectionInfoScreen(collectionId, collectionName)
    }

    fun upsertCollectionLocally(localCollectionEntity: LocalCollectionEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            upsertCollectionLocallyUseCase(localCollectionEntity)
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

    private val collectionsListLiveData = MutableLiveData<List<LocalCollectionEntity>>()

    fun getCollectionsListLiveData(): LiveData<List<LocalCollectionEntity>> =
        collectionsListLiveData

    fun getCollectionsList(onErrorAppearance: (errorModel: ErrorModel) -> Unit) {
        viewModelScope.launch {
            getCollectionsListUseCase()
                .onSuccess {
                    launch(Dispatchers.IO) {
                        val list = mutableListOf<LocalCollectionEntity>()
                        for (entity in it) {
                            list.add(mapCollectionEntityToLocalCollectionEntity(entity))
                        }
                        collectionsListLiveData.postValue(list)
                    }
                }
                .onFailure { onErrorAppearance(setupErrorCode(it)) }
        }
    }

    private fun mapCollectionEntityToLocalCollectionEntity(
        collectionEntity: CollectionEntity,
    ): LocalCollectionEntity {
        val localCollection = getCollectionByIdUseCase(collectionId = collectionEntity.id)
        val imageId = localCollection?.collectionImageId ?: collectionsIconsIds.random()
        val isFavourite = localCollection?.isFavourite ?: false

        return LocalCollectionEntity(
            collectionId = collectionEntity.id,
            collectionName = collectionEntity.name,
            collectionImageId = imageId,
            isFavourite = isFavourite,
            userEmail = getCurrentUserEmailUseCase()
        )
    }

    private fun setupErrorCode(e: Throwable): ErrorModel {
        return when (e) {
            is HttpException -> ErrorModel(e.code(), e.message(), e)
            is NoConnectivityException -> ErrorModel(e.code(), null, e)
            else -> ErrorModel(0, e.message, e)
        }
    }
}