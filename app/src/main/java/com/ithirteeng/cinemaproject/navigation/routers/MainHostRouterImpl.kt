package com.ithirteeng.cinemaproject.navigation.routers

import com.ithirteeng.features.collections.CollectionsFragment
import com.ithirteeng.features.compilation.ui.CompilationFragment
import com.ithirteeng.features.main.ui.MainFragment
import com.ithirteeng.features.mainhost.presentation.MainHostRouter
import com.ithirteeng.features.mainhost.utils.SectionType
import com.ithirteeng.features.profile.ProfileFragment

class MainHostRouterImpl(
    private val router: MainHostCustomRouter,
) : MainHostRouter {
    override fun navigateToMainScreen() {
        router.openSection(MainFragment.provideMainScreen, SectionType.MAIN)
    }

    override fun navigateToCompilationScreen() {
        router.openSection(CompilationFragment.provideCompilationScreen, SectionType.COMPILATION)
    }

    override fun navigateToProfileScreen() {
        router.openSection(ProfileFragment.provideProfileScreen, SectionType.PROFILE)
    }

    override fun navigateToCollectionsScreen() {
        router.openSection(CollectionsFragment.provideCollectionsScreen, SectionType.COLLECTIONS)
    }

    override fun exit() {
        router.exit()
    }


}