package com.ithirteeng.shared.userstorage.data.datasource

import com.ithirteeng.shared.userstorage.domain.entity.UserEntity

interface UserLocalDatasource {
    fun setUserEntryFlag(ifUserEnteredTheApp: Boolean)

    fun checkIfUserEnteredTheApp(): Boolean

    fun clearUserData()

    fun getUserData(): UserEntity?

    fun saveUserData(userEntity: UserEntity)
}