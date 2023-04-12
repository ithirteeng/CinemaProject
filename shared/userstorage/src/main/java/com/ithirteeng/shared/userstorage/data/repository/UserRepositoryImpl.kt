package com.ithirteeng.shared.userstorage.data.repository

import com.ithirteeng.shared.userstorage.data.datasource.UserLocalDatasource
import com.ithirteeng.shared.userstorage.domain.entity.UserEntity
import com.ithirteeng.shared.userstorage.domain.repository.UserRepository

class UserRepositoryImpl(
    private val localDatasource: UserLocalDatasource,
) : UserRepository {
    override fun setUserEntryFlag(ifUserEnteredTheApp: Boolean) =
        localDatasource.setUserEntryFlag(ifUserEnteredTheApp)

    override fun checkIfUserEnteredTheApp(): Boolean =
        localDatasource.checkIfUserEnteredTheApp()

    override fun clearProfileData() =
        localDatasource.clearProfileData()

    override fun clearUserData() =
        localDatasource.clearUserData()

    override fun getUserData(): UserEntity? =
        localDatasource.getUserData()

    override fun saveUserData(userEntity: UserEntity) =
        localDatasource.saveUserData(userEntity)

    override fun setCurrentUserEmail(email: String) =
        localDatasource.setCurrentUserEmail(email)

    override fun getCurrentUserEmail(): String =
        localDatasource.getCurrentUserEmail()

}