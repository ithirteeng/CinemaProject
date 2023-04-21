package com.ithirteeng.features.main.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.main.domain.entity.PosterEntity
import com.ithirteeng.features.main.domain.usecase.GetHistoryUseCase
import com.ithirteeng.features.main.domain.usecase.GetMainPosterUseCase
import com.ithirteeng.features.main.domain.usecase.GetMoviesListUseCase
import com.ithirteeng.shared.movies.entity.EpisodeViewEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType
import com.ithirteeng.shared.network.common.NoConnectivityException
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainFragmentViewModel(
    application: Application,
    private val router: MainRouter,
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val getHistoryUseCase: GetHistoryUseCase,
    private val getMainPosterUseCase: GetMainPosterUseCase,
) : AndroidViewModel(application) {

    fun navigateToMovieScreen(movieEntity: MovieEntity, moviesListType: MoviesListType) =
        router.navigateToMovieScreen(movieEntity, moviesListType)

    fun navigateToEpisodeScreen(
        episodeViewEntity: EpisodeViewEntity,
        movieListType: MoviesListType,
    ) = router.navigateToEpisodeScreen(
        episodeViewEntity.episodeId,
        episodeViewEntity.movieId,
        episodeViewEntity.movieName,
        movieListType
    )


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

    private val posterLiveData = MutableLiveData<PosterEntity>()

    private var posterCachedData: PosterEntity? = null

    fun getPosterLiveData(): LiveData<PosterEntity> = posterLiveData

    fun makeGetPosterRequest(onErrorAppearance: (errorModel: ErrorModel) -> Unit) {
        if (posterCachedData != null) {
            posterLiveData.value = posterCachedData!!
        } else {
            viewModelScope.launch {
                getMainPosterUseCase()
                    .onSuccess {
                        posterCachedData = transformPosterEntity(it.backgroundImage)
                        posterLiveData.value = transformPosterEntity(it.backgroundImage)
                    }
                    .onFailure {
                        onErrorAppearance(setupErrorCode(it))
                    }
            }
        }
    }

    private fun transformPosterEntity(posterUrl: String): PosterEntity {
        val newUrl = posterUrl.replace("\t", "")
        return PosterEntity(newUrl, newUrl)
    }

    private val inTrendMoviesLiveData = MutableLiveData<List<MovieEntity>>()

    private var inTrendMoviesCachedData: List<MovieEntity>? = null

    fun getInTrendMoviesLiveData(): LiveData<List<MovieEntity>> = inTrendMoviesLiveData

    fun makeGetInTrendMoviesListRequest(onErrorAppearance: (errorModel: ErrorModel) -> Unit) {
        if (inTrendMoviesCachedData != null) {
            inTrendMoviesLiveData.value = inTrendMoviesCachedData!!
        } else {
            makeGetMoviesListRequest(MoviesListType.IN_TREND, onErrorAppearance) {
                inTrendMoviesCachedData = it
                inTrendMoviesLiveData.value = it
            }
        }
    }

    private val newMoviesLiveData = MutableLiveData<List<MovieEntity>>()

    private var newMoviesCachedData: List<MovieEntity>? = null

    fun getNewMoviesLiveData(): LiveData<List<MovieEntity>> = newMoviesLiveData

    fun makeGetNewMoviesListRequest(onErrorAppearance: (errorModel: ErrorModel) -> Unit) {
        if (newMoviesCachedData != null) {
            newMoviesLiveData.value = newMoviesCachedData!!
        } else {
            makeGetMoviesListRequest(MoviesListType.NEW, onErrorAppearance) {
                newMoviesCachedData = it
                newMoviesLiveData.value = it
            }
        }
    }

    private val forYouMoviesLiveData = MutableLiveData<List<MovieEntity>>()

    private var forYouMoviesCachedData: List<MovieEntity>? = null

    fun getForYouMoviesLiveData(): LiveData<List<MovieEntity>> = forYouMoviesLiveData

    fun makeGetForYouMoviesListRequest(onErrorAppearance: (errorModel: ErrorModel) -> Unit) {
        if (forYouMoviesCachedData != null) {
            forYouMoviesLiveData.value = forYouMoviesCachedData!!
        } else {
            makeGetMoviesListRequest(MoviesListType.FOR_ME, onErrorAppearance) {
                forYouMoviesCachedData = it
                forYouMoviesLiveData.value = it
            }
        }
    }


    private fun makeGetMoviesListRequest(
        movieListType: MoviesListType,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
        doOnSuccess: (List<MovieEntity>) -> Unit,
    ) {
        viewModelScope.launch {
            getMoviesListUseCase(movieListType)
                .onSuccess {
                    doOnSuccess(it)
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
