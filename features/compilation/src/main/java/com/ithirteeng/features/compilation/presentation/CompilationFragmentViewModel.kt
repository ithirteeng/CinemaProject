package com.ithirteeng.features.compilation.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.compilation.domain.usecase.DeleteMovieFromCompilationUseCase
import com.ithirteeng.features.compilation.domain.usecase.GetCompilationMoviesListUseCase
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.network.common.NoConnectivityException
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CompilationFragmentViewModel(
    application: Application,
    private val router: CompilationRouter,
    private val getCompilationMoviesListUseCase: GetCompilationMoviesListUseCase,
    private val deleteMovieFromCompilationUseCase: DeleteMovieFromCompilationUseCase,
) : AndroidViewModel(application) {

    fun navigateToMovieInfoScreen() {
        router.navigateToMovieInfoScreen()
    }

    fun deleteMovieFromCompilation(
        movieId: String,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            deleteMovieFromCompilationUseCase(movieId)
                .onFailure {
                    onErrorAppearance(setupErrorCode(it))
                }
        }
    }


    private val moviesListLiveData = MutableLiveData<List<MovieEntity>>()

    fun getMoviesListLiveData(): MutableLiveData<List<MovieEntity>> = moviesListLiveData

    fun makeGetMoviesListRequest(onErrorAppearance: (errorModel: ErrorModel) -> Unit) {
        viewModelScope.launch {
            getCompilationMoviesListUseCase()
                .onSuccess {
                    moviesListLiveData.value = it
                }
                .onFailure {
                    onErrorAppearance(setupErrorCode(it))
                }
        }
    }

    private fun setupErrorCode(e: Throwable): ErrorModel {
        return when (e) {
            is HttpException -> ErrorModel(e.code(), e.message(), e)
            is NoConnectivityException -> ErrorModel(e.code(), null, e)
            else -> ErrorModel(0, e.message, e)
        }
    }
}