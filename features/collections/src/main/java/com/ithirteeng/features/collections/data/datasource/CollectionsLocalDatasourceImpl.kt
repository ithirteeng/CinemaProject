package com.ithirteeng.features.collections.data.datasource

import com.ithirteeng.features.collections.data.storage.CollectionSharedPreferences

class CollectionsLocalDatasourceImpl(
    private val sharedPreferences: CollectionSharedPreferences,
) : CollectionsLocalDatasource {

    override fun setCreationFavouritesFlag(creationFlag: Boolean, userName: String) =
        sharedPreferences.setFavouritesCreationFlag(creationFlag, userName)

    override fun getFavouritesCreationFlag(userName: String): Boolean =
        sharedPreferences.getFavouritesCreationFlag(userName)
}