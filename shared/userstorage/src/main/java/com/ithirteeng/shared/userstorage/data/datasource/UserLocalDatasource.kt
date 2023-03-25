package com.ithirteeng.shared.userstorage.data.datasource

interface UserLocalDatasource {
    fun setUserEntryFlag(ifUserEnteredTheApp: Boolean)

    fun checkIfUserEnteredTheApp(): Boolean
}