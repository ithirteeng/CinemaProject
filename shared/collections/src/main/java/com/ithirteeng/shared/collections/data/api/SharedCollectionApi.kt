package com.ithirteeng.shared.collections.data.api

import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import retrofit2.http.POST

interface SharedCollectionApi {
    @POST("/collections")
    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): CollectionEntity
}