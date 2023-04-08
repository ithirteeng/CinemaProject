package com.ithirteeng.features.collections.domain.repository

import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity

interface CollectionsRepository {
    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): Result<CollectionEntity>

    suspend fun getCollectionsList(): Result<List<CollectionEntity>>
}