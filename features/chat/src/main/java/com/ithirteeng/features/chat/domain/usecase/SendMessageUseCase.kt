package com.ithirteeng.features.chat.domain.usecase

import com.ithirteeng.features.chat.domain.repository.ChatRepository

class SendMessageUseCase(
    private val repository: ChatRepository
) {
    operator fun invoke(message: String) =
        repository.sendMessage(message)
}