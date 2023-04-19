package com.ithirteeng.features.chat.domain.repository

import com.ithirteeng.features.chat.domain.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getMessagesFlow(): Flow<MessageEntity?>

    fun sendMessage(message: String)

    fun closeSocket()

    fun initSocket(chatId: String)

    suspend fun getUserId(): Result<String>
}