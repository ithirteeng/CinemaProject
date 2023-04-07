package com.ithirteeng.features.collections.data.repository

import com.ithirteeng.features.collections.data.datasource.CollectionsLocalDatasource
import com.ithirteeng.features.collections.data.datasource.CollectionsRemoteDatasource
import com.ithirteeng.features.collections.domain.repository.CollectionsRepository
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity

class CollectionsRepositoryImpl(
    private val localDatasource: CollectionsLocalDatasource,
    private val remoteDatasource: CollectionsRemoteDatasource,
) : CollectionsRepository {

    override fun setCreationFavouritesFlag(creationFlag: Boolean, userName: String) =
        localDatasource.setCreationFavouritesFlag(creationFlag, userName)

    override fun getFavouritesCreationFlag(userName: String): Boolean =
        localDatasource.getFavouritesCreationFlag(userName)

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