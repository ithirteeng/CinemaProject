package com.ithirteeng.features.discussions.domain.repository

import com.ithirteeng.features.discussions.domain.entity.ChatEntity

interface DiscussionsRepository {
    suspend fun getChatsList(): Result<List<ChatEntity>>
}