package com.ithirteeng.features.mainhost.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ithirteeng.features.mainhost.utils.SectionType

class MainHostFragmentViewModel(
    application: Application,
    private val router: MainHostRouter
) : AndroidViewModel(application) {

    val sectionLiveData = MutableLiveData<SectionType>()

    fun navigateToMainScreen() =
        router.navigateToMainScreen()

    fun navigateToCompilationScreen() =
        router.navigateToCompilationScreen()

    fun navigateToProfileScreen() =
        router.navigateToProfileScreen()

    fun navigateToCollectionsScreen() =
        router.navigateToCollectionsScreen()

    fun exit() =
        router.exit()

}