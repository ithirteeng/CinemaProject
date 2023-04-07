package com.ithirteeng.features.collections.data.storage

import android.content.Context

class CollectionSharedPreferences(context: Context) {
    private companion object {
        const val FAVOURITES_STORAGE_NAME = "FAVOURITES_STORAGE_NAME"
    }

    private val sharedPreferences =
        context.getSharedPreferences(FAVOURITES_STORAGE_NAME, Context.MODE_PRIVATE)

    fun setFavouritesCreationFlag(creationFlag: Boolean, userName: String) {
        sharedPreferences.edit()
            .putBoolean(userName, creationFlag)
            .apply()
    }

    fun getFavouritesCreationFlag(userName: String): Boolean =
        sharedPreferences.getBoolean(userName, false)

}