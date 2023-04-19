package com.ithirteeng.features.chat.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.features.chat.domain.entity.MessageEntity
import com.ithirteeng.features.chat.domain.usecase.CloseSocketUseCase
import com.ithirteeng.features.chat.domain.usecase.GetMessagesFlowUseCase
import com.ithirteeng.features.chat.domain.usecase.InitSocketUseCase
import com.ithirteeng.features.chat.domain.usecase.SendMessageUseCase
import com.ithirteeng.features.chat.presentation.router.ChatRouter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChatFragmentViewModel(
    private val router: ChatRouter,
    private val initSocketUseCase: InitSocketUseCase,
    private val closeSocketUseCase: CloseSocketUseCase,
    private val getMessagesFlowUseCase: GetMessagesFlowUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
) : ViewModel() {

    fun exit() {
        router.exit()
    }

    fun initSocket(chatId: String) {
        initSocketUseCase(chatId)
    }

    fun sendMessage(message: String) {
        sendMessageUseCase(message)
    }

    fun closeSocket() {
        closeSocketUseCase()
    }

    private val onGettingMessagesLiveData = MutableLiveData<List<MessageEntity?>>()

    fun getMessagesListLiveData(): LiveData<List<MessageEntity?>> = onGettingMessagesLiveData

    private val messagesList = ArrayList<MessageEntity?>()

    fun getMessagesList() {
        viewModelScope.launch {
            getMessagesFlowUseCase().collect {
                messagesList.add(it)
                onGettingMessagesLiveData.value = messagesList
            }
        }
    }
}