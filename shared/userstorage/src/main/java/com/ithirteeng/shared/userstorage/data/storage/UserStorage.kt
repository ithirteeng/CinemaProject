package com.ithirteeng.shared.userstorage.data.storage

import android.content.Context

class UserStorage(context: Context) {

    companion object {
        const val USER_STORAGE_NAME = "user storage name"
        const val USER_ENTRY_FLAG = "have user been authorized"
    }

    private val sharedPreferences =
        context.getSharedPreferences(USER_STORAGE_NAME, Context.MODE_PRIVATE)

    fun setUserEntryFlag(ifUserPassedRegistration: Boolean) {
        sharedPreferences.edit()
            .putBoolean(USER_ENTRY_FLAG, ifUserPassedRegistration)
            .apply()
    }

    fun checkIfUserEnteredTheApp(): Boolean {
        return sharedPreferences.getBoolean(USER_ENTRY_FLAG, false)
    }
}