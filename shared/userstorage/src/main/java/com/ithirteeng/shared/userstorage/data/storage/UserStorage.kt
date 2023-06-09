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
        const val CURRENT_USER_KEY = "CURRENT_USER_KEY"
    }

    private val sharedPreferences =
        context.getSharedPreferences(USER_STORAGE_NAME, Context.MODE_PRIVATE)

    fun setUserEntryFlag(ifUserPassedRegistration: Boolean) {
        sharedPreferences.edit()
            .putBoolean(USER_ENTRY_FLAG, ifUserPassedRegistration)
            .apply()
    }

    fun setCurrentUserEmail(email: String) {
        sharedPreferences.edit().putString(CURRENT_USER_KEY, email).apply()
    }

    fun getCurrentUserEmail(): String {
        return sharedPreferences.getString(CURRENT_USER_KEY, null).toString()
    }

    fun checkIfUserEnteredTheApp(): Boolean {
        return sharedPreferences.getBoolean(USER_ENTRY_FLAG, false)
    }

    fun saveUserData(userEntity: UserEntity) {
        sharedPreferences.edit()
            .putString(USER_NAME_SURNAME_KEY, userEntity.fullName)
            .putString(USER_EMAIL_KEY, userEntity.email)
            .putString(USER_IMAGE_KEY, userEntity.image)
            .apply()
    }

    fun clearUserStorage() {
        sharedPreferences.edit().clear().apply()
    }

    fun clearUserData() {
        sharedPreferences.edit()
            .remove(USER_IMAGE_KEY)
            .remove(USER_EMAIL_KEY)
            .remove(USER_NAME_SURNAME_KEY)
            .apply()
    }

    fun getUserData(): UserEntity? {
        return if (sharedPreferences.getString(USER_IMAGE_KEY, null) == null) {
            null
        } else {
            UserEntity(
                fullName = sharedPreferences.getString(USER_NAME_SURNAME_KEY, null),
                email = sharedPreferences.getString(USER_EMAIL_KEY, null),
                image = sharedPreferences.getString(USER_IMAGE_KEY, null)
            )
        }
    }
}