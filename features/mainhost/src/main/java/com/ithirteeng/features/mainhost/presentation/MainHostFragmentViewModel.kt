package com.ithirteeng.features.mainhost.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainHostFragmentViewModel(
    application: Application,
    private val router: MainHostRouter
) : AndroidViewModel(application) {

    fun navigateToMainScreen() =
        router.navigateToMainScreen()

    fun navigateToCompilationScreen() =
        router.navigateToCompilationScreen()

    fun navigateToProfileScreen() =
        router.navigateToProfileScreen()

    fun navigateToCollectionsScreen() =
        router.navigateToCollectionsScreen()

}