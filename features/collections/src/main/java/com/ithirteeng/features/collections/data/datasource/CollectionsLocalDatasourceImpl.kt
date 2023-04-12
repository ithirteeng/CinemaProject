package com.ithirteeng.features.collections.data.datasource

import com.ithirteeng.features.collections.data.storage.IconSharedPreferences

class CollectionsLocalDatasourceImpl(
    private val sharedPreferences: IconSharedPreferences,
) : CollectionsLocalDatasource {

    override fun saveImageId(imageId: Int) =
        sharedPreferences.putImageId(imageId)

    override fun getImageId(): Int =
        sharedPreferences.getImageId()

    override fun clearStorage() =
        sharedPreferences.clearImageStorage()
}