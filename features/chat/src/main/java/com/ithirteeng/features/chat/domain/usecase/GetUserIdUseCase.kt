package com.ithirteeng.features.chat.domain.usecase

import com.ithirteeng.features.chat.domain.repository.ChatRepository

class GetUserIdUseCase(
    private val repository: ChatRepository,
) {
    suspend operator fun invoke(): Result<String> =
        repository.getUserId()
}