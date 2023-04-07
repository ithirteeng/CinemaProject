package com.ithirteeng.features.collections.data.datasource

import com.ithirteeng.features.collections.domain.entity.CollectionEntity
import com.ithirteeng.features.collections.domain.entity.CreateCollectionEntity

interface CollectionsRemoteDatasource {
    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): CollectionEntity
}