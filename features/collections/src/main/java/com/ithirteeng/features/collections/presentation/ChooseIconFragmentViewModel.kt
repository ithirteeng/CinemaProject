package com.ithirteeng.features.collections.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.features.collections.domain.usecase.SaveImageIdLocallyUseCase
import com.ithirteeng.features.collections.presentation.routers.ChooseIconRouter

class ChooseIconFragmentViewModel(
    private val router: ChooseIconRouter,
    private val saveImageIdLocallyUseCase: SaveImageIdLocallyUseCase
) : ViewModel() {

    fun exit(imageId: Int) {
        saveImageIdLocallyUseCase(imageId)
        router.exit()
    }
}