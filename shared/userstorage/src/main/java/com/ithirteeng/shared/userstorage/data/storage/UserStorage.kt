package com.ithirteeng.shared.userstorage.data.storage

import android.content.Context
import com.ithirteeng.shared.userstorage.domain.entity.UserEntity

class UserStorage(context: Context) {

    private companion object {
        const val USER_STORAGE_NAME = "user storage name"
        const val USER_ENTRY_FLAG = "have user been authorized"
        const val USER_NAME_SURNAME_KEY = "USER_NAME_SURNAME_KEY"
        const val USER_EMAIL_KEY = "USER_EMAIL_KEY"
        const val USER_IMAGE_KEY = "USER_IMAGE_KEY"
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

    fun saveUserData(userEntity: UserEntity) {
        sharedPreferences.edit()
            .putString(USER_NAME_SURNAME_KEY, userEntity.fullName)
            .putString(USER_EMAIL_KEY, userEntity.email)
            .putString(USER_IMAGE_KEY, userEntity.imageUri)
            .apply()
    }

    fun clearUserStorage() {
        sharedPreferences.edit().clear().apply()
    }

    fun getUserData(): UserEntity? {
        return if (sharedPreferences.getString(USER_NAME_SURNAME_KEY, null) == null) {
            null
        } else {
            UserEntity(
                fullName = sharedPreferences.getString(USER_NAME_SURNAME_KEY, null),
                email = sharedPreferences.getString(USER_EMAIL_KEY, null),
                imageUri = sharedPreferences.getString(USER_IMAGE_KEY, null)
            )
        }
    }
}