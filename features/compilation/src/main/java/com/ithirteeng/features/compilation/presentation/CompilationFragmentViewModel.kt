package com.ithirteeng.features.compilation.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ithirteeng.component.design.R.string.add_collection_409
import com.ithirteeng.customextensions.presentation.SingleEventLiveData
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.compilation.domain.entity.MovieIdEntity
import com.ithirteeng.features.compilation.domain.usecase.AddMovieToFavouritesUseCase
import com.ithirteeng.features.compilation.domain.usecase.DeleteMovieFromCompilationUseCase
import com.ithirteeng.features.compilation.domain.usecase.GetCompilationMoviesListUseCase
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.domain.usecase.GetFavouritesCollectionUseCase
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.network.common.NoConnectivityException
import com.ithirteeng.shared.userstorage.domain.usecase.GetCurrentUserEmailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CompilationFragmentViewModel(
    private val application: Application,
    private val router: CompilationRouter,
    private val getCompilationMoviesListUseCase: GetCompilationMoviesListUseCase,
    private val deleteMovieFromCompilationUseCase: DeleteMovieFromCompilationUseCase,
    private val getFavouritesCollectionUseCase: GetFavouritesCollectionUseCase,
    private val addMovieToFavouritesUseCase: AddMovieToFavouritesUseCase,
    private val getCurrentUserEmailUseCase: GetCurrentUserEmailUseCase,
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

    private val favouritesCollectionLiveData = MutableLiveData<LocalCollectionEntity?>()

    fun getCollection(): LiveData<LocalCollectionEntity?> {
        viewModelScope.launch(Dispatchers.IO) {
            val entity = getFavouritesCollectionUseCase(getCurrentUserEmailUseCase())
            favouritesCollectionLiveData.postValue(entity)
        }

        return favouritesCollectionLiveData
    }

    fun addMoviesToFavourite(
        movieId: String,
        collectionId: String,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            addMovieToFavouritesUseCase(MovieIdEntity(movieId), collectionId)
                .onSuccess { deleteMovieFromCompilation(movieId, onErrorAppearance) }
                .onFailure {
                    if (it is HttpException && it.code() == 409) {
                        deleteMovieFromCompilation(movieId, onErrorAppearance)
                    } else {
                        onErrorAppearance(setupErrorCode(it))
                    }
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
            is NoConnectivityException -> {
                var errorDescription: String? = application.getString(add_collection_409)
                if (e.code() != 409) {
                    errorDescription = null
                }
                ErrorModel(e.code(), errorDescription, e)
            }
            else -> ErrorModel(0, e.message, e)
        }
    }
}