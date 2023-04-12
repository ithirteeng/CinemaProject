package com.ithirteeng.features.collections.presentation.routers

import com.ithirteeng.features.collections.presentation.utils.ChooseIconReason

interface ChangeCollectionRouter {

    fun exit()

    fun navigateToChooseIconScreen(chooseIconReason: ChooseIconReason)
}