package com.ithirteeng.cinemaproject.network.authenticator

import com.ithirteeng.shared.network.common.AUTHORIZATION_HEADER
import com.ithirteeng.shared.network.common.BEARER
import com.ithirteeng.shared.token.domain.usecase.GetTokenFromLocalStorageUseCase
import com.ithirteeng.shared.token.domain.usecase.RefreshTokenUseCase
import com.ithirteeng.shared.token.domain.usecase.SaveTokenToLocalStorageUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val localToken = getTokenFromLocalStorageUseCase()

        val remoteToken = runBlocking {
            refreshTokenUseCase(localToken?.refreshToken)
        }

        saveTokenToLocalStorageUseCase(remoteToken)

        return if (response.responseCount >= 1) {
            null
        } else {
            response.request.newBuilder()
                .authorizationHeader(remoteToken?.accessToken.toString())
                .build()
        }
    }

    private val Response.responseCount: Int
        get() = generateSequence(this) { it.priorResponse }.count()

    private fun Request.Builder.authorizationHeader(accessToken: String): Request.Builder {
        return header(
            AUTHORIZATION_HEADER,
            "$BEARER $accessToken"
        )
    }
}
