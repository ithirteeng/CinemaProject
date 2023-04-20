package com.ithirteeng.features.chat.domain.usecase

import com.ithirteeng.features.chat.domain.repository.ChatRepository

class CloseSocketUseCase(
    private val repository: ChatRepository,
) {
    operator fun invoke() =
        repository.closeSocket()
}