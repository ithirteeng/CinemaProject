package com.ithirteeng.features.collections.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.features.collections.domain.usecase.*
import com.ithirteeng.features.collections.presentation.routers.ChangeCollectionRouter
import com.ithirteeng.features.collections.presentation.utils.ChooseIconReason
import com.ithirteeng.shared.collections.domain.usecase.DeleteCollectionLocallyUseCase
import com.ithirteeng.shared.collections.domain.usecase.UpsertCollectionLocallyUseCase
import com.ithirteeng.shared.userstorage.domain.usecase.GetCurrentUserEmailUseCase

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
}