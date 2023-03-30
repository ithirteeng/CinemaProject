package com.ithirteeng.cinemaproject.navigation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.collections.CollectionsFragment
import com.ithirteeng.features.compilation.ui.CompilationFragment
import com.ithirteeng.features.main.ui.MainFragment
import com.ithirteeng.features.mainhost.utils.SectionType
import com.ithirteeng.features.profile.ProfileFragment

class MainHostCustomRouter(
    private val hostRouter: Router,
) {
    private val backstack = mutableListOf<FragmentScreen>()

    private val currentSection = MutableLiveData<SectionType>()

    fun newRootScreen(screen: FragmentScreen, sectionType: SectionType) {
        hostRouter.newRootScreen(screen)
        backstack.clear()
        backstack.add(screen)
        currentSection.value = sectionType
    }

    fun openSection(screen: FragmentScreen, sectionType: SectionType) {
        if (backstack.isEmpty()) {
            newRootScreen(screen, sectionType)
        } else {
            if (sectionType == currentSection.value) {
                onMultipleSectionClick(sectionType)
            } else {
                navigateToCorrectSection(screen, sectionType)
            }
        }
        Log.d("BACKSTACK", backstack.toString())
    }

    private fun navigateToCorrectSection(screen: FragmentScreen, sectionType: SectionType) {
        if (screen in backstack) {
            val screenIndex = backstack.indexOf(screen) + 1
            val backstackSize = backstack.size
            for (i in screenIndex until backstackSize) {
                backstack.removeAt(i)
            }
            hostRouter.backTo(screen)
        } else {
            hostRouter.navigateTo(screen)
            backstack.add(screen)
        }
        currentSection.value = sectionType
    }


    private fun onMultipleSectionClick(sectionType: SectionType) {
        when (sectionType) {
            SectionType.MAIN -> newRootScreen(MainFragment.provideMainScreen, sectionType)
            SectionType.COMPILATION -> newRootScreen(
                CompilationFragment.provideCompilationScreen,
                sectionType
            )
            SectionType.COLLECTIONS -> newRootScreen(
                CollectionsFragment.provideCollectionsScreen,
                sectionType
            )
            else -> newRootScreen(ProfileFragment.provideProfileScreen, sectionType)
        }
    }

}