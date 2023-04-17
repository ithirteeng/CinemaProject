package com.ithirteeng.features.episode.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ithirteeng.component.design.R.string.movie_collection_error
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.episode.domain.usecase.*
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.domain.usecase.GetCollectionsListUseCase
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.network.common.NoConnectivityException
import com.ithirteeng.shared.userstorage.domain.usecase.GetCurrentUserEmailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class EpisodeFragmentViewModel(
    private val application: Application,
    private val getEpisodeTimeUseCase: GetEpisodeTimeUseCase,
    private val getEpisodesListUseCase: GetEpisodesListUseCase,
    private val getEpisodeUseCase: GetEpisodeUseCase,
    private val setEpisodeTimeUseCase: SetEpisodeTimeUseCase,
    private val getEpisodesYearsUseCase: GetEpisodesYearsUseCase,
    private val addMovieToCollectionUseCase: AddMovieToCollectionUseCase,
    private val getCollectionsListUseCase: GetCollectionsListUseCase,
    private val getUserEmailUseCase: GetCurrentUserEmailUseCase,
    private val router: EpisodeRouter,
) : AndroidViewModel(application) {

    fun exit() {
        router.exit()
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

    private val successfulRequestLiveData = MutableLiveData<Boolean>()

    fun getSuccessfulRequestLiveData(): LiveData<Boolean> = successfulRequestLiveData

    fun setEpisodeTime(
        episodeId: String,
        timeInSeconds: Int,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            setEpisodeTimeUseCase(episodeId, timeInSeconds.toString())
                .onSuccess { successfulRequestLiveData.value = true }
                .onFailure { onErrorAppearance(setupErrorCode(it)) }
        }
    }


    private val collectionsListLiveData = MutableLiveData<List<LocalCollectionEntity>?>()

    fun getCollectionsListLiveData(): LiveData<List<LocalCollectionEntity>?> =
        collectionsListLiveData

    fun getCollectionsList() {
        viewModelScope.launch(Dispatchers.IO) {
            collectionsListLiveData.postValue(getCollectionsListUseCase(getUserEmailUseCase()))
        }
    }


    private val addToCollectionResultLiveData = MutableLiveData<Boolean>()

    fun getAddToCollectionResultLiveData(): LiveData<Boolean> = addToCollectionResultLiveData

    fun addMovieToCollection(
        movieId: String,
        collectionId: String,
        onErrorAppearance: (errorModel: ErrorModel) -> Unit,
    ) {
        viewModelScope.launch {
            addMovieToCollectionUseCase.invoke(movieId, collectionId)
                .onSuccess {
                    addToCollectionResultLiveData.value = true
                }
                .onFailure { onErrorAppearance(setupErrorCode(it)) }
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