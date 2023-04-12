package com.ithirteeng.features.collections.presentation.routers

import com.ithirteeng.features.collections.presentation.utils.ChooseIconReason

interface CreateCollectionRouter {
    fun exit()

    fun navigateToChooseIconScreen(chooseIconReason: ChooseIconReason)
}