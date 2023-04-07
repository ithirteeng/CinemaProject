package com.ithirteeng.features.collections.data.api

import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface CollectionsApi {
    @POST("/api/collections")
    suspend fun createCollection(@Body createCollectionEntity: CreateCollectionEntity): CollectionEntity
}