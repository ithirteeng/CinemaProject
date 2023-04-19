package com.ithirteeng.features.chat.data.datasource

import kotlinx.coroutines.flow.SharedFlow

interface ChatRemoteDatasource {
    fun getMessages(): SharedFlow<String>

    fun sendMessage(message: String)

    fun closeSocket()

    fun initSocket(chatId: String)
}