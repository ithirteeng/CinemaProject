package com.ithirteeng.shared.userstorage.domain.repository

import com.ithirteeng.shared.userstorage.domain.entity.UserEntity

interface UserRepository {
    fun setUserEntryFlag(ifUserEnteredTheApp: Boolean)

    fun checkIfUserEnteredTheApp(): Boolean

    fun clearProfileData()

    fun clearUserData()

    fun getUserData(): UserEntity?

    fun saveUserData(userEntity: UserEntity)

    fun setCurrentUserEmail(email: String)

    fun getCurrentUserEmail(): String
}