package com.ithirteeng.shared.collections.datasource

import com.ithirteeng.shared.collections.data.storage.CollectionsDatabase
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity

class SharedCollectionsLocalDatasourceImpl(
    private val database: CollectionsDatabase,
) : SharedCollectionsLocalDatasource {

    override fun getCollectionById(id: String): LocalCollectionEntity? =
        database.collectionDao().getCollectionImageId(id)

    override fun saveCollectionLocally(localCollectionEntity: LocalCollectionEntity) =
        database.collectionDao().saveCollectionData(localCollectionEntity)

    override fun updateCollectionLocally(localCollectionEntity: LocalCollectionEntity) =
        database.collectionDao().updateCollection(localCollectionEntity)

    override fun deleteCollectionLocally(localCollectionEntity: LocalCollectionEntity) =
        database.collectionDao().deleteCollection(localCollectionEntity)

    override fun deleteCollectionLocally(collectionId: String) =
        database.collectionDao().deleteCollectionById(collectionId)

    override fun getFavouritesCollection(): LocalCollectionEntity? =
        database.collectionDao().getFavouritesCollection()
}