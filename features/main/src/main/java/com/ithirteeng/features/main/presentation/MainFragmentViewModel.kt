package com.ithirteeng.features.main.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ithirteeng.features.main.domain.usecase.GetHistoryUseCase
import com.ithirteeng.features.main.domain.usecase.GetMovieEpisodesListUseCase
import com.ithirteeng.features.main.domain.usecase.GetMoviesListUseCase

class MainFragmentViewModel(
    application: Application,
    private val router: MainRouter,
    private val getMovieEpisodesListUseCase: GetMovieEpisodesListUseCase,
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val getHistoryUseCase: GetHistoryUseCase,
) : AndroidViewModel(application) {

    fun navigateToMovieScreen() =
        router.navigateToMovieScreen()
}