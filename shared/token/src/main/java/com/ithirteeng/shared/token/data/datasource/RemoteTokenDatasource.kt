package com.ithirteeng.shared.token.data.datasource

import com.ithirteeng.shared.token.domain.entity.TokenEntity

interface RemoteTokenDatasource {
    suspend fun refreshToken(refreshToken: String?): TokenEntity
}