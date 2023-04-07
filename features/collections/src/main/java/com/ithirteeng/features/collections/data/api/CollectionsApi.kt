package com.ithirteeng.features.collections.data.api

import com.ithirteeng.features.collections.domain.entity.CollectionEntity
import com.ithirteeng.features.collections.domain.entity.CreateCollectionEntity
import retrofit2.http.POST

interface CollectionsApi {
    @POST("/collections")
    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): CollectionEntity
}