package com.ithirteeng.features.discussions.domain.usecase

import com.ithirteeng.features.discussions.domain.entity.ChatEntity
import com.ithirteeng.features.discussions.domain.repository.DiscussionsRepository

class GetChatsListUseCase(
    private val repository: DiscussionsRepository,
) {
    suspend operator fun invoke(): Result<List<ChatEntity>> =
        repository.getChatsList()
}