package com.ithirteeng.shared.collections.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ithirteeng.shared.collections.data.dao.CollectionDao
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity

@Database(entities = [LocalCollectionEntity::class], version = 1)
abstract class CollectionsDatabase : RoomDatabase() {
    abstract fun collectionDao(): CollectionDao
}