package com.ithirteeng.shared.collections.data.dao

import androidx.room.*
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity

@Dao
interface CollectionDao {

    @Query("SELECT * FROM collections_table WHERE collectionId = :collectionId")
    fun getCollectionImageId(collectionId: String): LocalCollectionEntity?

    @Upsert
    fun saveCollectionData(localCollectionEntity: LocalCollectionEntity)

    @Delete
    fun deleteCollection(localCollectionEntity: LocalCollectionEntity)

    @Query("DELETE FROM collections_table WHERE collectionId = :collectionId")
    fun deleteCollectionById(collectionId: String)

    @Update
    fun updateCollection(localCollectionEntity: LocalCollectionEntity)

    @Query("SELECT * FROM collections_table WHERE (isFavourite = 1) AND (userEmail = :userEmail)")
    fun getFavouritesCollection(userEmail: String): LocalCollectionEntity?
}