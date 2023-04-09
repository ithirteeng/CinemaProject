package com.ithirteeng.features.collections.data.repository

import com.ithirteeng.features.collections.data.datasource.CollectionsRemoteDatasource
import com.ithirteeng.features.collections.domain.repository.CollectionsRepository
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity

class CollectionsRepositoryImpl(
    private val remoteDatasource: CollectionsRemoteDatasource,
) : CollectionsRepository {

    override suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): Result<CollectionEntity> {
        return try {
            Result.success(remoteDatasource.createCollection(createCollectionEntity))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCollectionsList(): Result<List<CollectionEntity>> {
        return try {
            Result.success(remoteDatasource.getCollectionsList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}