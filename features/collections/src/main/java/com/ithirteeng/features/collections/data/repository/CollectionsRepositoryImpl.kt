package com.ithirteeng.features.collections.data.repository

import com.ithirteeng.features.collections.data.datasource.CollectionsLocalDatasource
import com.ithirteeng.features.collections.data.datasource.CollectionsRemoteDatasource
import com.ithirteeng.features.collections.domain.entity.CollectionEntity
import com.ithirteeng.features.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.features.collections.domain.repository.CollectionsRepository

class CollectionsRepositoryImpl(
    private val localDatasource: CollectionsLocalDatasource,
    private val remoteDatasource: CollectionsRemoteDatasource,
) : CollectionsRepository {

    override fun setCreationFavouritesFlag(creationFlag: Boolean) =
        localDatasource.setCreationFavouritesFlag(creationFlag)

    override fun getFavouritesCreationFlag(): Boolean =
        localDatasource.getFavouritesCreationFlag()

    override suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): Result<CollectionEntity> {
        return try {
            Result.success(remoteDatasource.createCollection(createCollectionEntity))
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }
}