package com.ithirteeng.features.mainhost.presentation

import com.ithirteeng.features.mainhost.utils.SectionType

interface MainHostRouter {

    fun navigateToMainScreen()

    fun navigateToCompilationScreen()

    fun navigateToProfileScreen()

    fun navigateToCollectionsScreen()

    fun exit()

    fun getCurrentSection(): SectionType
}