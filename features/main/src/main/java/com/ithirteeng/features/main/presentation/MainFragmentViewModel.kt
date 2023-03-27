package com.ithirteeng.features.main.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.main.domain.usecase.GetHistoryUseCase
import com.ithirteeng.features.main.domain.usecase.GetMovieEpisodesListUseCase
import com.ithirteeng.features.main.domain.usecase.GetMoviesListUseCase
import com.ithirteeng.features.main.domain.utils.MoviesListType
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.EpisodeViewEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.network.common.NoConnectivityException
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainFragmentViewModel(
    application: Application,
    private val router: MainRouter,
    private val getMovieEpisodesListUseCase: GetMovieEpisodesListUseCase,
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val getHistoryUseCase: GetHistoryUseCase,
) : AndroidViewModel(application) {

    fun navigateToMovieScreen() =
        router.navigateToMovieScreen()


    private val moviesLiveData = MutableLiveData<List<MovieEntity>>()

    fun getMoviesLiveData(): LiveData<List<MovieEntity>> = moviesLiveData

    fun makeGetMoviesListRequest(
        movieListType: MoviesListType,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            getMoviesListUseCase(movieListType)
                .onSuccess {
                    moviesLiveData.value = it
                }
                .onFailure {
                    onErrorAppearance(setupErrorCode(it))
                }
        }
    }


    private val movieEpisodesLiveData = MutableLiveData<List<EpisodeEntity>>()

    fun getMovieEpisodesLiveData(): LiveData<List<EpisodeEntity>> = movieEpisodesLiveData

    fun makeGetMovieEpisodesListRequest(
        movieId: String,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            getMovieEpisodesListUseCase(movieId)
                .onSuccess {
                    movieEpisodesLiveData.value = it
                }
                .onFailure {
                    onErrorAppearance(setupErrorCode(it))
                }
        }
    }


    private val historyLiveData = MutableLiveData<List<EpisodeViewEntity>>()

    fun getHistoryLiveData(): LiveData<List<EpisodeViewEntity>> = historyLiveData

    fun makeGetHistoryRequest(onErrorAppearance: (errorModel: ErrorModel) -> Unit) {
        viewModelScope.launch {
            getHistoryUseCase()
                .onSuccess {
                    historyLiveData.value = it
                }
                .onFailure {
                    onErrorAppearance(setupErrorCode(it))
                }
        }
    }


    private fun setupErrorCode(e: Throwable): ErrorModel {
        return when (e) {
            is HttpException -> ErrorModel(e.code(), e.message())
            is NoConnectivityException -> ErrorModel(e.code())
            else -> ErrorModel(0, e.message)
        }
    }
}