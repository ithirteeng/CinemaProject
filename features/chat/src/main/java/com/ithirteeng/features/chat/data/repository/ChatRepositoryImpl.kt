package com.ithirteeng.features.chat.data.repository

import com.ithirteeng.features.chat.data.datasource.ChatRemoteDatasource
import com.ithirteeng.features.chat.data.helper.JsonHelper
import com.ithirteeng.features.chat.domain.entity.MessageEntity
import com.ithirteeng.features.chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class ChatRepositoryImpl(
    private val remoteDatasource: ChatRemoteDatasource,
) : ChatRepository {
    override fun getMessagesFlow(): Flow<MessageEntity?> {
        return flow {
            remoteDatasource.getMessages().collect {
                emit(JsonHelper.jsonToMessageEntity(it))
            }
        }
    }

    override fun initSocket(chatId: String) {
        remoteDatasource.initSocket(chatId)
    }

    override suspend fun getUserId(): Result<String> {
        return try {
            Result.success(remoteDatasource.getUserId())
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }

    override fun sendMessage(message: String) {
        remoteDatasource.sendMessage(message)
    }

    override fun closeSocket() {
        remoteDatasource.closeSocket()
    }
}