package com.ithirteeng.features.collections.data.datasource

interface CollectionsLocalDatasource {

    fun setCreationFavouritesFlag(creationFlag: Boolean, userName: String)

    fun getFavouritesCreationFlag(userName: String): Boolean
}