package com.ithirteeng.features.movieinfo.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.movieinfo.domain.usecase.GetMovieEpisodesListUseCase
import com.ithirteeng.features.movieinfo.domain.usecase.GetMoviesListUseCase
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType
import com.ithirteeng.shared.network.common.NoConnectivityException
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MovieFragmentViewModel(
    application: Application,
    private val getMovieEpisodesListUseCase: GetMovieEpisodesListUseCase,
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val router: MovieRouter,
) : AndroidViewModel(application) {

    fun navigateToChatScreen(chatId: String) =
        router.navigateToChatScreen(chatId)

    fun navigateToEpisodeScreen(episodeId: String) =
        router.navigateToEpisodeScreen(episodeId)

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


    private val movieLiveData = MutableLiveData<MovieEntity?>()

    fun getMovieLiveData(): LiveData<MovieEntity?> = movieLiveData

    fun makeGetMoviesListRequest(
        movieId: String,
        moviesListType: MoviesListType,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            getMoviesListUseCase(moviesListType)
                .onSuccess {
                    movieLiveData.value = getMovieById(movieId, it)
                }
                .onFailure {
                    onErrorAppearance(setupErrorCode(it))
                }
        }
    }

    private fun getMovieById(movieId: String, moviesList: List<MovieEntity>): MovieEntity? {
        return moviesList.find { it.id == movieId }
    }


    private fun setupErrorCode(e: Throwable): ErrorModel {
        return when (e) {
            is HttpException -> ErrorModel(e.code(), e.message(), e)
            is NoConnectivityException -> ErrorModel(e.code(), null, e)
            else -> ErrorModel(0, e.message, e)
        }
    }
}