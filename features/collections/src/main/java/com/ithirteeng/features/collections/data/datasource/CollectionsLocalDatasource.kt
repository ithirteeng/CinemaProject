package com.ithirteeng.features.collections.data.datasource

interface CollectionsLocalDatasource {
    fun saveImageId(imageId: Int)

    fun getImageId(): Int

    fun clearStorage()
}