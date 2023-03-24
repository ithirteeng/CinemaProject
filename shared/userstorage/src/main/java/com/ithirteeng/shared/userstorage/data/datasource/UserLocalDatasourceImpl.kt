package com.ithirteeng.shared.userstorage.data.datasource

import com.ithirteeng.shared.userstorage.data.storage.UserStorage

class UserLocalDatasourceImpl(
    private val storage: UserStorage
) : UserLocalDatasource {
    override fun setUserEntryFlag(ifUserEnteredTheApp: Boolean) =
        storage.setUserEntryFlag(ifUserEnteredTheApp)

    override fun checkIfUserEnteredTheApp(): Boolean =
        storage.checkIfUserEnteredTheApp()
}