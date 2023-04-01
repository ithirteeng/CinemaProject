package com.ithirteeng.cinemaproject.navigation.routers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.collections.CollectionsFragment
import com.ithirteeng.features.compilation.ui.CompilationFragment
import com.ithirteeng.features.main.ui.MainFragment
import com.ithirteeng.features.mainhost.utils.SectionType
import com.ithirteeng.features.profile.ui.ProfileFragment

class MainHostCustomRouter(
    private val hostRouter: Router,
) {
    private val screensBackstack = mutableListOf<FragmentScreen>()
    private val sectionsBackstack = mutableListOf<SectionType>()

    private val currentSection = MutableLiveData<SectionType>()

    fun getCurrentSectionType(): SectionType? = sectionsBackstack.lastOrNull()

    private fun newRootScreen(screen: FragmentScreen, sectionType: SectionType) {
        hostRouter.newRootScreen(screen)
        screensBackstack.clear()
        sectionsBackstack.clear()
        screensBackstack.add(screen)
        sectionsBackstack.add(sectionType)
        currentSection.value = sectionType
    }

    fun openSection(screen: FragmentScreen, sectionType: SectionType) {
        if (screensBackstack.isEmpty()) {
            newRootScreen(screen, sectionType)
        } else {
            if (sectionType == currentSection.value) {
                onMultipleSectionClick(sectionType)
            } else {
                navigateToSection(screen, sectionType)
            }
        }
        Log.d("BACKSTACK", sectionsBackstack.toString())
    }

    private fun navigateToSection(screen: FragmentScreen, sectionType: SectionType) {
        if (screen in screensBackstack) {
            val screenIndex = screensBackstack.indexOf(screen) + 1
            val backstackSize = screensBackstack.size
            for (i in 0 until backstackSize - screenIndex) {
                screensBackstack.removeLast()
                sectionsBackstack.removeLast()
            }
            hostRouter.backTo(screen)
        } else {
            hostRouter.navigateTo(screen)
            screensBackstack.add(screen)
            sectionsBackstack.add(sectionType)
        }
        currentSection.value = sectionType
    }

    private fun replaceLastScreen(screen: FragmentScreen, sectionType: SectionType) {
        screensBackstack.removeLast()
        sectionsBackstack.removeLast()

        screensBackstack.add(screen)
        sectionsBackstack.add(sectionType)

        hostRouter.replaceScreen(screen)
    }

    private fun onMultipleSectionClick(sectionType: SectionType) {
        when (sectionType) {
            SectionType.MAIN -> replaceLastScreen(MainFragment.provideMainScreen, sectionType)
            SectionType.COMPILATION -> replaceLastScreen(
                CompilationFragment.provideCompilationScreen,
                sectionType
            )
            SectionType.COLLECTIONS -> replaceLastScreen(
                CollectionsFragment.provideCollectionsScreen,
                sectionType
            )
            else -> replaceLastScreen(ProfileFragment.provideProfileScreen, sectionType)
        }
    }

    fun exit() {
        sectionsBackstack.removeLast()
        screensBackstack.removeLast()
        if (sectionsBackstack.isNotEmpty()) currentSection.value = sectionsBackstack.last()
        hostRouter.exit()
        Log.d("BACKSTACK", sectionsBackstack.toString())
    }

}