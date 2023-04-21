package com.ithirteeng.shared.token.data.datasource

import com.ithirteeng.shared.token.data.storage.TokenStorage
import com.ithirteeng.shared.token.domain.entity.TokenEntity

class LocalTokenDatasourceImpl(
    private val storage: TokenStorage
) : LocalTokenDatasource {
    override fun getTokenFromLocalStorage(): TokenEntity? =
        storage.getTokens()

    override fun saveTokenToLocalStorage(tokenEntity: TokenEntity?) =
        storage.saveTokens(tokenEntity)


    override fun removeTokenFromLocalStorage() =
        storage.clearTokens()

    override fun checkTokenExistence(): Boolean =
        storage.checkTokenExistence()

}