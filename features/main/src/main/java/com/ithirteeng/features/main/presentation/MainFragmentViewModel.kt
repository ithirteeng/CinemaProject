package com.ithirteeng.features.main.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainFragmentViewModel(
    application: Application,
    private val router: MainRouter
) : AndroidViewModel(application) {

    fun navigateToMovieScreen() =
        router.navigateToMovieScreen()
}