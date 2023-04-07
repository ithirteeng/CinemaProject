package com.ithirteeng.shared.collections.data.datasource

import com.ithirteeng.shared.collections.data.storage.CollectionSharedPreferences

class SharedCollectionLocalDatasourceImpl(
    private val sharedPreferences: CollectionSharedPreferences,
) : SharedCollectionLocalDatasource {

    override fun setCreationFavouritesFlag(creationFlag: Boolean) =
        sharedPreferences.setFavouritesCreationFlag(creationFlag)

    override fun getFavouritesCreationFlag(): Boolean =
        sharedPreferences.getFavouritesCreationFlag()
}