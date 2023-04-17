package com.ithirteeng.shared.collections.data.repository

import com.ithirteeng.shared.collections.data.datasource.SharedCollectionsLocalDatasource
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository

class SharedCollectionRepositoryImpl(
    private val localDatasource: SharedCollectionsLocalDatasource,
) : SharedCollectionRepository {

    override fun getCollectionById(id: String): LocalCollectionEntity? =
        localDatasource.getCollectionById(id)

    override fun saveCollectionLocally(localCollectionEntity: LocalCollectionEntity) =
        localDatasource.saveCollectionLocally(localCollectionEntity)

    override fun updateCollectionLocally(localCollectionEntity: LocalCollectionEntity) =
        localDatasource.updateCollectionLocally(localCollectionEntity)

    override fun deleteCollectionLocally(localCollectionEntity: LocalCollectionEntity) =
        localDatasource.deleteCollectionLocally(localCollectionEntity)

    override fun deleteCollectionLocally(collectionId: String) =
        localDatasource.deleteCollectionLocally(collectionId)

    override fun getFavouritesCollection(userEmail: String): LocalCollectionEntity? =
        localDatasource.getFavouritesCollection(userEmail)

    override fun getCollectionsByEmail(userEmail: String): List<LocalCollectionEntity>? =
        localDatasource.getCollectionsByEmail(userEmail)
}