package com.ithirteeng.shared.collections.data.repository

import com.ithirteeng.shared.collections.data.datasource.SharedCollectionLocalDatasource
import com.ithirteeng.shared.collections.data.datasource.SharedCollectionRemoteDatasource
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository

class SharedCollectionRepositoryImpl(
    private val localDatasource: SharedCollectionLocalDatasource,
    private val remoteDatasource: SharedCollectionRemoteDatasource,
) : SharedCollectionRepository {

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