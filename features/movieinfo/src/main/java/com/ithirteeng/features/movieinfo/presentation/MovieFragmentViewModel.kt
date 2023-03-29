package com.ithirteeng.features.movieinfo.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ithirteeng.features.movieinfo.domain.usecase.GetMovieEpisodesListUseCase
import com.ithirteeng.features.movieinfo.domain.usecase.GetMoviesListUseCase

class MovieFragmentViewModel(
    application: Application,
    private val getMovieEpisodesListUseCase: GetMovieEpisodesListUseCase,
    private val getMoviesListUseCase: GetMoviesListUseCase,
) : AndroidViewModel(application) {
}