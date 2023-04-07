package com.ithirteeng.features.collections.data.datasource

import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity

interface CollectionsRemoteDatasource {
    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): CollectionEntity
}