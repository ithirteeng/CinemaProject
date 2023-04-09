package com.ithirteeng.features.collections.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.collections.domain.usecase.GetCollectionMoviesListUseCase
import com.ithirteeng.features.collections.presentation.routers.CollectionInfoRouter
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.network.common.NoConnectivityException
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CollectionInfoFragmentViewModel(
    private val getCollectionMoviesListUseCase: GetCollectionMoviesListUseCase,
    private val router: CollectionInfoRouter,
) : ViewModel() {

    fun exit() {
        router.exit()
    }

    fun navigateToMovieInfoScreen(movieEntity: MovieEntity) {
        router.navigateToMovieInfoScreen(movieEntity)
    }

    fun navigateToChangeCollectionScreen() {
        router.navigateToChangeCollectionScreen()
    }

    private val moviesListLiveData = MutableLiveData<List<MovieEntity>>()

    private var moviesCachedData: List<MovieEntity>? = null

    fun getMovieListLiveData(): LiveData<List<MovieEntity>> = moviesListLiveData

    fun makeGetMoviesListRequest(
        collectionId: String,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            if (moviesCachedData == null) {
                getCollectionMoviesListUseCase(collectionId)
                    .onSuccess {
                        moviesCachedData = it
                        moviesListLiveData.value = it
                    }
                    .onFailure {
                        onErrorAppearance(setupErrorCode(it))
                    }
            } else {
                moviesListLiveData.value = moviesCachedData!!
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