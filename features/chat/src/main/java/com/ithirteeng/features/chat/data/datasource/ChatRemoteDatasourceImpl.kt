package com.ithirteeng.features.chat.data.datasource

import com.ithirteeng.features.chat.data.api.ChatWebSocket
import kotlinx.coroutines.flow.SharedFlow

class ChatRemoteDatasourceImpl(
    private val socket: ChatWebSocket,
) : ChatRemoteDatasource {

    override fun getMessages(): SharedFlow<String> {
        return socket.getGetMessagesFlow()
    }

    override fun initSocket(chatId: String) {
        socket.initSocket(chatId)
    }

    override fun sendMessage(message: String) {
        socket.getSocket().send(message)
    }

    override fun closeSocket() {
        socket.getSocket().close(1000, "don't worry 'bout the things")
    }
}