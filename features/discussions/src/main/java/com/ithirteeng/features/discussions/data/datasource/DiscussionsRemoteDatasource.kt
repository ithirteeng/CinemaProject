package com.ithirteeng.features.discussions.data.datasource

import com.ithirteeng.features.discussions.domain.entity.ChatEntity

interface DiscussionsRemoteDatasource {
    suspend fun getChatsList(): List<ChatEntity>
}