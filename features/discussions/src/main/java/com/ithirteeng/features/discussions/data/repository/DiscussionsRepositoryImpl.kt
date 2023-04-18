package com.ithirteeng.features.discussions.data.repository

import com.ithirteeng.features.discussions.data.datasource.DiscussionsRemoteDatasource
import com.ithirteeng.features.discussions.domain.entity.ChatEntity
import com.ithirteeng.features.discussions.domain.repository.DiscussionsRepository

class DiscussionsRepositoryImpl(
    private val remoteDatasource: DiscussionsRemoteDatasource,
) : DiscussionsRepository {

    override suspend fun getChatsList(): Result<List<ChatEntity>> {
        return try {
            Result.success(remoteDatasource.getChatsList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}