package com.ithirteeng.features.collections.data.datasource

import com.ithirteeng.features.collections.data.api.CollectionsApi
import com.ithirteeng.features.collections.domain.entity.CollectionEntity
import com.ithirteeng.features.collections.domain.entity.CreateCollectionEntity

class CollectionsRemoteDatasourceImpl(
    private val api: CollectionsApi,
) : CollectionsRemoteDatasource {

    override suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): CollectionEntity =
        api.createCollection(createCollectionEntity)
}