package com.ithirteeng.features.collections.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.features.collections.presentation.routers.ChangeCollectionRouter
import com.ithirteeng.features.collections.presentation.utils.ChooseIconReason

class ChangeCollectionFragmentViewModel(
    private val router: ChangeCollectionRouter,
) : ViewModel() {

    fun exit() {
        router.exit()
    }

    fun navigateToChooseIconScreen() {
        router.navigateToChooseIconScreen(ChooseIconReason.CHANGE)
    }
}