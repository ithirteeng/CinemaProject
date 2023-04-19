package com.ithirteeng.features.chat.domain.usecase

import com.ithirteeng.features.chat.domain.repository.ChatRepository

class GetMessagesFlowUseCase(
    private val repository: ChatRepository,
) {
    operator fun invoke() =
        repository.getMessagesFlow()
}