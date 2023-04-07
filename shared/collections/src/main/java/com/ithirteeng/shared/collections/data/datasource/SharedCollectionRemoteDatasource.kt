package com.ithirteeng.shared.collections.data.datasource

import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity

interface SharedCollectionRemoteDatasource {
    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): CollectionEntity
}