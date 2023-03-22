package com.ithirteeng.shared.token.data.datasource

import com.ithirteeng.shared.token.data.api.TokenApi

class RemoteTokenDatasourceImpl(
    private val api: TokenApi
) : RemoteTokenDatasource {
    override suspend fun refreshToken(refreshToken: String?) =
        api.refreshToken(refreshToken)

}