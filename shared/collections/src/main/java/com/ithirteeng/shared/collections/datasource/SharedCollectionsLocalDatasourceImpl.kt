package com.ithirteeng.shared.collections.datasource

import com.ithirteeng.shared.collections.data.storage.CollectionSharedPreferences
import com.ithirteeng.shared.collections.data.storage.CollectionsDatabase
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity

class SharedCollectionsLocalDatasourceImpl(
    private val database: CollectionsDatabase,
    private val sharedPreferences: CollectionSharedPreferences,
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

    override fun setCreationFavouritesFlag(creationFlag: Boolean, userName: String) =
        sharedPreferences.setFavouritesCreationFlag(creationFlag, userName)

    override fun getFavouritesCreationFlag(userName: String): Boolean =
        sharedPreferences.getFavouritesCreationFlag(userName)
}