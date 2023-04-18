package com.ithirteeng.features.discussions.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.discussions.domain.entity.ChatEntity
import com.ithirteeng.features.discussions.domain.usecase.GetChatsListUseCase
import com.ithirteeng.features.discussions.presentation.router.DiscussionsRouter
import com.ithirteeng.shared.network.common.NoConnectivityException
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DiscussionsFragmentViewModel(
    private val getChatsListUseCase: GetChatsListUseCase,
    private val router: DiscussionsRouter,
) : ViewModel() {

    fun exit() {
        router.exit()
    }

    private val chatsListLiveData = MutableLiveData<List<ChatEntity>>()

    fun getChatsLiveData(): LiveData<List<ChatEntity>> = chatsListLiveData

    fun makeGetChatsRequest(onErrorAppearance: (errorModel: ErrorModel) -> Unit) {
        viewModelScope.launch {
            getChatsListUseCase()
                .onSuccess {
                    chatsListLiveData.value = it
                }
                .onFailure { onErrorAppearance(setupErrorCode(it)) }
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