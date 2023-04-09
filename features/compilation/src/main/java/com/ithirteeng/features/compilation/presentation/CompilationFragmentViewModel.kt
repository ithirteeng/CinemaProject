package com.ithirteeng.features.compilation.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ithirteeng.customextensions.presentation.SingleEventLiveData
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.compilation.domain.usecase.DeleteMovieFromCompilationUseCase
import com.ithirteeng.features.compilation.domain.usecase.GetCompilationMoviesListUseCase
import com.ithirteeng.shared.collections.domain.usecase.GetFavouritesCollectionUseCase
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.network.common.NoConnectivityException
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CompilationFragmentViewModel(
    application: Application,
    private val router: CompilationRouter,
    private val getCompilationMoviesListUseCase: GetCompilationMoviesListUseCase,
    private val deleteMovieFromCompilationUseCase: DeleteMovieFromCompilationUseCase,
    private val getFavouritesCollectionUseCase: GetFavouritesCollectionUseCase
) : AndroidViewModel(application) {

    fun navigateToMovieInfoScreen(movieEntity: MovieEntity) {
        router.navigateToMovieInfoScreen(movieEntity)
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


    private val moviesListLiveData = SingleEventLiveData<List<MovieEntity>>()

    fun getMoviesListLiveData(): LiveData<List<MovieEntity>> = moviesListLiveData

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