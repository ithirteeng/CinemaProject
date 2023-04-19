package com.ithirteeng.features.chat.data.api

import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.runBlocking
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class ChatWebSocketListener : WebSocketListener() {

    private val flow = MutableSharedFlow<String>()

    fun getFlow() = flow

    override fun onOpen(webSocket: WebSocket, response: Response) {
        Log.d("!", "OPENED!")
        runBlocking {
            flow.emit(response.message)
        }

        super.onOpen(webSocket, response)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        runBlocking {
            flow.emit(text)
        }
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        Log.d("!", "Closing!  $code")
        super.onClosing(webSocket, code, reason)
    }
}