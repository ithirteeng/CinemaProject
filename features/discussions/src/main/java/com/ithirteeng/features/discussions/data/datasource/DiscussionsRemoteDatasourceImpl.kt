package com.ithirteeng.features.discussions.data.datasource

import com.ithirteeng.features.discussions.data.api.DiscussionsApi
import com.ithirteeng.features.discussions.domain.entity.ChatEntity

class DiscussionsRemoteDatasourceImpl(
    private val api: DiscussionsApi,
) : DiscussionsRemoteDatasource {
    override suspend fun getChatsList(): List<ChatEntity> =
        api.getChatsList()

}