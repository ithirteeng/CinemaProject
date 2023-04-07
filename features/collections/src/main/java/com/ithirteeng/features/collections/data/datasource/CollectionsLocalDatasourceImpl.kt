package com.ithirteeng.features.collections.data.datasource

import com.ithirteeng.features.collections.data.storage.CollectionSharedPreferences

class CollectionsLocalDatasourceImpl(
    private val sharedPreferences: CollectionSharedPreferences,
) : CollectionsLocalDatasource {

    override fun setCreationFavouritesFlag(creationFlag: Boolean) =
        sharedPreferences.setFavouritesCreationFlag(creationFlag)

    override fun getFavouritesCreationFlag(): Boolean =
        sharedPreferences.getFavouritesCreationFlag()
}