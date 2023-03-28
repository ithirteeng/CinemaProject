package com.ithirteeng.features.compilation.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ithirteeng.features.compilation.domain.usecase.DeleteMovieFromCompilationUseCase
import com.ithirteeng.features.compilation.domain.usecase.GetCompilationMoviesListUseCase

class CompilationFragmentViewModel(
    application: Application,
    private val router: CompilationRouter,
    private val getCompilationMoviesListUseCase: GetCompilationMoviesListUseCase,
    private val deleteMovieFromCompilationUseCase: DeleteMovieFromCompilationUseCase,
) : AndroidViewModel(application) {

}