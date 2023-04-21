package com.ithirteeng.features.chat.domain.usecase

import com.ithirteeng.features.chat.domain.repository.ChatRepository

class InitSocketUseCase(
    private val repository: ChatRepository,
) {
    operator fun invoke(chatId: String) =
        repository.initSocket(chatId)
}