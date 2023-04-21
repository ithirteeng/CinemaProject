package com.ithirteeng.features.chat.domain.usecase

import com.ithirteeng.features.chat.domain.entity.MessageEntity
import com.ithirteeng.features.chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class GetMessagesFlowUseCase(
    private val repository: ChatRepository,
) {
    operator fun invoke(): Flow<MessageEntity?> =
        repository.getMessagesFlow()
}