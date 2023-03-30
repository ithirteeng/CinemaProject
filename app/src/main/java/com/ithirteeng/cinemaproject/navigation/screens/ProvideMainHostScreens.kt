package com.ithirteeng.cinemaproject.navigation.screens

import com.ithirteeng.features.compilation.ui.CompilationFragment
import com.ithirteeng.features.main.ui.MainFragment
import com.ithirteeng.features.mainhost.utils.SectionType

val provideMainScreen = SectionScreen(SectionType.MAIN) { MainFragment() }

val provideCompilationScreen = SectionScreen(SectionType.COMPILATION) { CompilationFragment() }