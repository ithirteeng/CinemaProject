package com.ithirteeng.shared.collections.data.repository

import com.ithirteeng.shared.collections.datasource.SharedCollectionsLocalDatasource
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository

class SharedCollectionRepositoryImpl(
    private val localDatasource: SharedCollectionsLocalDatasource,
) : SharedCollectionRepository {
    override fun getCollectionImageIdById(id: String): Int =
        localDatasource.getCollectionImageIdById(id)

    override fun saveCollectionLocally(localCollectionEntity: LocalCollectionEntity) =
        localDatasource.saveCollectionLocally(localCollectionEntity)

    override fun updateCollectionLocally(localCollectionEntity: LocalCollectionEntity) =
        localDatasource.updateCollectionLocally(localCollectionEntity)

    override fun deleteCollectionLocally(localCollectionEntity: LocalCollectionEntity) =
        localDatasource.deleteCollectionLocally(localCollectionEntity)

    override fun deleteCollectionLocally(collectionId: String) =
        localDatasource.deleteCollectionLocally(collectionId)
}