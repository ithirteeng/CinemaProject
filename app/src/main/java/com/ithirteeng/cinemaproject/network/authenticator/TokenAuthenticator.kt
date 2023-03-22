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

@Suppress("IMPLICIT_NOTHING_TYPE_ARGUMENT_AGAINST_NOT_NOTHING_EXPECTED_TYPE")
class TokenAuthenticator(
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase
) : Authenticator {


    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            getTokenFromLocalStorageUseCase()?.let { localToken ->
                refreshTokenUseCase(localToken.refreshToken)?.let { remoteToken ->
                    saveTokenToLocalStorageUseCase(remoteToken)
                    response.request.newBuilder()
                        .authorizationHeader(remoteToken.accessToken.toString())
                        .build()
                }
            } ?: kotlin.run {
                null
            }
        }
    }

    private fun Request.Builder.authorizationHeader(accessToken: String): Request.Builder {
        return header(
            AUTHORIZATION_HEADER,
            "$BEARER $accessToken"
        )
    }
}
