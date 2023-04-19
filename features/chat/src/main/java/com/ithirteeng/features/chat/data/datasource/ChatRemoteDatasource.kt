package com.ithirteeng.features.chat.data.datasource

import kotlinx.coroutines.flow.MutableSharedFlow

interface ChatRemoteDatasource {
    fun getMessages(): MutableSharedFlow<String>

    fun sendMessage(message: String, )

    fun closeSocket()

    fun initSocket(chatId: String)
}