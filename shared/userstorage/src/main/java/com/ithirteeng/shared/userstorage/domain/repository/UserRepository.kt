package com.ithirteeng.shared.userstorage.domain.repository

interface UserRepository {
    fun setUserEntryFlag(ifUserEnteredTheApp: Boolean)

    fun checkIfUserEnteredTheApp(): Boolean
}