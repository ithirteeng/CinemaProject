package com.ithirteeng.features.collections.data.datasource

interface CollectionsLocalDatasource {

    fun setCreationFavouritesFlag(creationFlag: Boolean)

    fun getFavouritesCreationFlag(): Boolean
}