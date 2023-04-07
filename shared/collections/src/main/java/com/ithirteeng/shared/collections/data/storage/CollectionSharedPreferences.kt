package com.ithirteeng.shared.collections.data.storage

import android.content.Context

class CollectionSharedPreferences(context: Context) {
    private companion object {
        const val FAVOURITES_FLAG = "FAVOURITES_FLAG"
        const val FAVOURITES_STORAGE_NAME = "FAVOURITES_STORAGE_NAME"
    }

    private val sharedPreferences =
        context.getSharedPreferences(FAVOURITES_STORAGE_NAME, Context.MODE_PRIVATE)

    fun setFavouritesCreationFlag(creationFlag: Boolean) {
        sharedPreferences.edit()
            .putBoolean(FAVOURITES_FLAG, creationFlag)
            .apply()
    }

    fun getFavouritesCreationFlag(): Boolean =
        sharedPreferences.getBoolean(FAVOURITES_FLAG, false)

    fun clearUserStorage() {
        sharedPreferences.edit().clear().apply()
    }
}