package com.ithirteeng.features.chat.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.features.chat.domain.entity.MessageEntity
import com.ithirteeng.features.chat.domain.model.Message
import com.ithirteeng.features.chat.domain.usecase.*
import com.ithirteeng.features.chat.presentation.router.ChatRouter
import com.ithirteeng.features.chat.presentation.utils.DateHelper
import com.ithirteeng.shared.network.common.NoConnectivityException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.*

class ChatFragmentViewModel(
    private val router: ChatRouter,
    private val initSocketUseCase: InitSocketUseCase,
    private val closeSocketUseCase: CloseSocketUseCase,
    private val getMessagesFlowUseCase: GetMessagesFlowUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
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

    private val onGettingMessagesLiveData = MutableLiveData<List<Message>>()

    fun getMessagesListLiveData(): LiveData<List<Message>> = onGettingMessagesLiveData

    private val messagesList = ArrayList<Message>()

    fun getMessagesList(userId: String) {
        viewModelScope.launch {
            getMessagesFlowUseCase().collect {
                val message = getCorrectMessage(userId, it)

                if (message !is Message.Error) {
                    addDateToList(it)
                    if (ifMessageFirst(message)) {
                        message.messageEntity?.isFirst = true
                    }
                    setAvatarToMessage(message)
                    messagesList.add(message)
                }
                onGettingMessagesLiveData.value = messagesList
            }
        }
    }

    private fun addDateToList(messageEntity: MessageEntity?) {
        val newMessageEntity = MessageEntity(
            messageId = UUID.randomUUID().toString(),
            creationDateTime = messageEntity?.creationDateTime.toString(),
            authorId = "",
            authorName = "",
            authorAvatar = "",
            text = ""
        )
        if (messagesList.isEmpty()) {
            messagesList.add(Message.DateMessage(newMessageEntity))
        } else {
            val newDateString = DateHelper.getDate(messageEntity?.creationDateTime.toString())
            val lastDateString =
                DateHelper.getDate(messagesList.last().messageEntity?.creationDateTime.toString())
            if (lastDateString != newDateString) {
                messagesList.add(Message.DateMessage(newMessageEntity))
            }
        }
    }

    private fun getCorrectMessage(userId: String, messageEntity: MessageEntity?): Message {
        return if (messageEntity == null) {
            Message.Error()
        } else if (messageEntity.authorId == userId) {
            Message.MineMessage(messageEntity)
        } else {
            Message.OthersMessage(messageEntity)
        }
    }

    private fun setAvatarToMessage(message: Message) {
        if (messagesList.last().messageEntity?.authorId == message.messageEntity?.authorId) {
            val newMessage = messagesList.last()
            newMessage.messageEntity?.authorAvatar = "BAD"

            messagesList.removeLast()
            messagesList.add(newMessage)
        }
    }

    private fun ifMessageFirst(message: Message): Boolean {
        return (message is Message.OthersMessage || message is Message.MineMessage) &&
                messagesList.last().messageEntity?.authorId != message.messageEntity?.authorId
    }

    private val userIdLiveData = MutableLiveData<String>()

    fun getUserIdLiveData(): LiveData<String> = userIdLiveData

    fun makeGetUserIdRequest(onErrorAppearance: (errorModel: ErrorModel) -> Unit) {
        viewModelScope.launch {
            getUserIdUseCase()
                .onSuccess {
                    userIdLiveData.value = it
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