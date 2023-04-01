package com.ithirteeng.shared.userstorage.data.datasource

import com.ithirteeng.shared.userstorage.data.storage.UserStorage
import com.ithirteeng.shared.userstorage.domain.entity.UserEntity

class UserLocalDatasourceImpl(
    private val storage: UserStorage,
) : UserLocalDatasource {
    override fun setUserEntryFlag(ifUserEnteredTheApp: Boolean) =
        storage.setUserEntryFlag(ifUserEnteredTheApp)

    override fun checkIfUserEnteredTheApp(): Boolean =
        storage.checkIfUserEnteredTheApp()

    override fun clearUserData() =
        storage.clearUserStorage()

    override fun getUserData(): UserEntity? =
        storage.getUserData()

    override fun saveUserData(userEntity: UserEntity) =
        storage.saveUserData(userEntity)
}