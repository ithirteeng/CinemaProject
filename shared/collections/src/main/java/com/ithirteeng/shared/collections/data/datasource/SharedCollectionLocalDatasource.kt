package com.ithirteeng.shared.collections.data.datasource

interface SharedCollectionLocalDatasource {

    fun setCreationFavouritesFlag(creationFlag: Boolean)

    fun getFavouritesCreationFlag(): Boolean
}