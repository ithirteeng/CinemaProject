package com.ithirteeng.shared.token.data.datasource

import com.ithirteeng.shared.token.domain.entity.TokenEntity

interface LocalTokenDatasource {

    fun getTokenFromLocalStorage(): TokenEntity?

    fun saveTokenToLocalStorage(tokenEntity: TokenEntity?)

    fun removeTokenFromLocalStorage()

    fun checkTokenExistence(): Boolean
}