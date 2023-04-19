package com.ithirteeng.features.chat.data.api

import com.ithirteeng.features.chat.BuildConfig
import kotlinx.coroutines.flow.SharedFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class ChatWebSocket(
    private val okHttpClient: OkHttpClient,
    private val webSocketListener: ChatWebSocketListener,
) {

    private lateinit var socket: WebSocket

    fun initSocket(chatId: String) {
        val url = BuildConfig.BASE_URL.replace("http", "ws") + "chats/$chatId/messages"

        val request = Request.Builder()
            .url(url)
            .build()

        socket = okHttpClient.newWebSocket(request, webSocketListener)
    }

    fun getSocket(): WebSocket = socket

    fun getGetMessagesFlow(): SharedFlow<String> = webSocketListener.getFlow()

}