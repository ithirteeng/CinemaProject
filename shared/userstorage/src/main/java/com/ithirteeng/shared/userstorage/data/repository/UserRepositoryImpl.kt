package com.ithirteeng.shared.userstorage.data.repository

import com.ithirteeng.shared.userstorage.data.datasource.UserLocalDatasource
import com.ithirteeng.shared.userstorage.domain.repository.UserRepository

class UserRepositoryImpl(
    private val localDatasource: UserLocalDatasource
) : UserRepository {
    override fun setUserEntryFlag(ifUserEnteredTheApp: Boolean) =
        localDatasource.setUserEntryFlag(ifUserEnteredTheApp)

    override fun checkIfUserEnteredTheApp(): Boolean =
        localDatasource.checkIfUserEnteredTheApp()
}