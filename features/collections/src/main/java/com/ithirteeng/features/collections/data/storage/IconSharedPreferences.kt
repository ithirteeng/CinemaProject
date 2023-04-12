package com.ithirteeng.features.collections.data.storage

import android.content.Context

class IconSharedPreferences(context: Context) {

    private companion object {
        const val ICON_SHARED_PREFS_NAME = "ICON_SHARED_PREFS_NAME"
        const val ICON_ID_KEY = "ICON_ID_KEY"
    }

    private val sharedPreferences =
        context.getSharedPreferences(ICON_SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun putImageId(imageId: Int) {
        sharedPreferences.edit()
            .putInt(ICON_ID_KEY, imageId)
            .apply()
    }

    fun getImageId(): Int {
        return sharedPreferences.getInt(ICON_ID_KEY, -1)
    }

    fun clearImageStorage() {
        sharedPreferences.edit().clear().apply()
    }
}