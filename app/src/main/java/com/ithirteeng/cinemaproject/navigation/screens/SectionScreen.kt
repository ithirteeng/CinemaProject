package com.ithirteeng.cinemaproject.navigation.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.mainhost.utils.SectionType

class SectionScreen(
    val sectionType: SectionType,
    val createScreen: () -> Fragment
) : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment = createScreen()

}