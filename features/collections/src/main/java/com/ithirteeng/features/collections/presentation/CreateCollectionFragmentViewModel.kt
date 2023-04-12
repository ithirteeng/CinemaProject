package com.ithirteeng.features.collections.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.collections.domain.usecase.ClearImageStorageUseCase
import com.ithirteeng.features.collections.domain.usecase.CreateCollectionUseCase
import com.ithirteeng.features.collections.domain.usecase.GetImageIdUseCase
import com.ithirteeng.features.collections.presentation.routers.CreateCollectionRouter
import com.ithirteeng.features.collections.presentation.utils.ChooseIconReason
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.network.common.NoConnectivityException
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CreateCollectionFragmentViewModel(
    private val createCollectionUseCase: CreateCollectionUseCase,
    private val router: CreateCollectionRouter,
    private val getImageIdUseCase: GetImageIdUseCase,
    private val clearImageStorageUseCase: ClearImageStorageUseCase,
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