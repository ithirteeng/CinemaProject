package com.ithirteeng.features.episode.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ithirteeng.component.design.R.string.favourites_collection
import com.ithirteeng.component.design.R.string.movie_collection_error
import com.ithirteeng.customextensions.presentation.SingleEventLiveData
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.episode.domain.usecase.*
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.network.common.NoConnectivityException
import kotlinx.coroutines.launch
import retrofit2.HttpException

class EpisodeFragmentViewModel(
    private val application: Application,
    private val getMoviesInfoUseCase: GetMovieInfoUseCase,
    private val getEpisodeTimeUseCase: GetEpisodeTimeUseCase,
    private val getEpisodesListUseCase: GetEpisodesListUseCase,
    private val getEpisodeUseCase: GetEpisodeUseCase,
    private val setEpisodeTimeUseCase: SetEpisodeTimeUseCase,
    private val getEpisodesYearsUseCase: GetEpisodesYearsUseCase,
    private val addMovieToCollectionUseCase: AddMovieToCollectionUseCase,
    private val getCollectionsListUseCase: GetCollectionsListUseCase,
    private val router: EpisodeRouter,
) : AndroidViewModel(application) {

    fun exit() {
        router.exit()
    }

    fun navigateToChatScreen(chatId: String, chatName: String) {
        router.navigateToChatScreen(chatId, chatName)
    }


    private val episodeTimeLiveData = MutableLiveData<Int>()

    fun getEpisodeTimeLiveData(): LiveData<Int> = episodeTimeLiveData

    fun makeGetEpisodeTimeRequest(
        episodeId: String,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            getEpisodeTimeUseCase(episodeId)
                .onSuccess {
                    Log.d("TIMES", it.toInt().toString())
                    val time = it.toInt()
                    episodeTimeLiveData.value = time
                }
                .onFailure { onErrorAppearance(setupErrorCode(it)) }
        }
    }


    private val episodeLiveData = MutableLiveData<EpisodeEntity?>()

    fun getEpisodeLiveData(): LiveData<EpisodeEntity?> = episodeLiveData

    fun getEpisodeData(episodeId: String, episodes: List<EpisodeEntity>) {
        episodeLiveData.value = getEpisodeUseCase(episodeId, episodes)
    }


    private val yearsLiveData = MutableLiveData<String?>()

    fun getYearsLiveData(): LiveData<String?> = yearsLiveData

    fun setupMovieYears(episodes: List<EpisodeEntity>) {
        yearsLiveData.value = getEpisodesYearsUseCase(episodes)
    }


    private val episodesListLiveData = MutableLiveData<List<EpisodeEntity>>()

    fun getEpisodesListLiveData(): LiveData<List<EpisodeEntity>> = episodesListLiveData

    fun makeGetEpisodesListUseCase(
        movieId: String,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            getEpisodesListUseCase(movieId)
                .onSuccess {
                    episodesListLiveData.value = it
                }
                .onFailure {
                    onErrorAppearance(setupErrorCode(it))
                }
        }
    }


    private val successfulRequestLiveData = SingleEventLiveData<Boolean>()

    private var requestFlag = false

    fun getSuccessfulRequestLiveData(): LiveData<Boolean> = successfulRequestLiveData

    fun setEpisodeTime(
        episodeId: String,
        timeInSeconds: Int,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            setEpisodeTimeUseCase(episodeId, timeInSeconds.toString())
                .onSuccess {
                    successfulRequestLiveData.value = requestFlag
                    requestFlag = !requestFlag
                }
                .onFailure {
                    onErrorAppearance(setupErrorCode(it))
                    requestFlag != requestFlag
                }
        }
    }


    private val collectionsListLiveData = MutableLiveData<List<CollectionEntity>>()

    fun getCollectionsListLiveData(): LiveData<List<CollectionEntity>> =
        collectionsListLiveData

    fun getCollectionsList(onErrorAppearance: (errorModel: ErrorModel) -> Unit) {
        viewModelScope.launch {
            viewModelScope.launch {
                getCollectionsListUseCase()
                    .onSuccess {
                        collectionsListLiveData.value = it
                    }
                    .onFailure { onErrorAppearance(setupErrorCode(it)) }
            }
        }
    }


    private val addToCollectionResultLiveData = SingleEventLiveData<Boolean>()

    private var collectionFlag = false

    fun getAddToCollectionResultLiveData(): LiveData<Boolean> = addToCollectionResultLiveData

    fun addMovieToCollection(
        movieId: String,
        collectionId: String,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            addMovieToCollectionUseCase.invoke(movieId, collectionId)
                .onSuccess {
                    collectionFlag = !collectionFlag
                    addToCollectionResultLiveData.value = collectionFlag
                }
                .onFailure {
                    onErrorAppearance(setupErrorCode(it))
                    collectionFlag = !collectionFlag
                }
        }
    }


    private val movieInfoLiveData = MutableLiveData<MovieEntity>()

    fun getMovieInfoLiveData(): LiveData<MovieEntity> = movieInfoLiveData

    fun makeGetMovieInfoRequest(
        movieId: String,
        movieFilter: String,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            getMoviesInfoUseCase(movieId, movieFilter)
                .onSuccess {
                    movieInfoLiveData.value = it
                }
                .onFailure { onErrorAppearance(setupErrorCode(it)) }
        }
    }


    fun findFavouritesCollection(): CollectionEntity? {
        return collectionsListLiveData.value?.findLast {
            it.name == application.getString(favourites_collection)
        }
    }


    private fun setupErrorCode(e: Throwable): ErrorModel {
        return when (e) {
            is HttpException -> {
                if (e.code() == 409) {
                    ErrorModel(e.code(), application.getString(movie_collection_error))
                } else {
                    ErrorModel(e.code(), e.message(), e)
                }
            }
            is NoConnectivityException -> ErrorModel(e.code(), null, e)
            else -> ErrorModel(0, e.message, e)
        }
    }

}