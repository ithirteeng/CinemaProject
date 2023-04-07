package com.ithirteeng.shared.collections.data.datasource

import com.ithirteeng.shared.collections.data.api.SharedCollectionApi
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity

class SharedCollectionRemoteDatasourceImpl(
    private val api: SharedCollectionApi,
) : SharedCollectionRemoteDatasource {

    override suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): CollectionEntity =
        api.createCollection(createCollectionEntity)
}