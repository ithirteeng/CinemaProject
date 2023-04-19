package com.ithirteeng.features.chat.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.features.chat.domain.usecase.CloseSocketUseCase
import com.ithirteeng.features.chat.domain.usecase.GetMessagesFlowUseCase
import com.ithirteeng.features.chat.domain.usecase.InitSocketUseCase
import com.ithirteeng.features.chat.domain.usecase.SendMessageUseCase
import com.ithirteeng.features.chat.presentation.router.ChatRouter

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
}