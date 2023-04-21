package com.ithirteeng.shared.token.data.storage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.ithirteeng.shared.token.domain.entity.TokenEntity

class TokenStorage(
    context: Context
) {
    companion object {
        const val TOKEN_STORAGE_NAME = "token storage name"
        const val ACCESS_TOKEN_KEY = "access_token_key"
        const val REFRESH_TOKEN_KEY = "refresh_token_key"
    }

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        TOKEN_STORAGE_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveTokens(tokenEntity: TokenEntity?) {
        sharedPreferences.edit()
            .putString(ACCESS_TOKEN_KEY, tokenEntity?.accessToken)
            .putString(REFRESH_TOKEN_KEY, tokenEntity?.refreshToken)
            .apply()
    }

    fun getTokens(): TokenEntity? {
        return if (checkTokenExistence()) {
            TokenEntity(
                accessToken = sharedPreferences.getString(ACCESS_TOKEN_KEY, null),
                refreshToken = sharedPreferences.getString(REFRESH_TOKEN_KEY, null),
                accessTokenExpirationTime = null,
                refreshTokenExpirationTime = null
            )
        } else {
            null
        }

    }

    fun checkTokenExistence(): Boolean {
        val token = sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
        return token != null
    }

    fun clearTokens() {
        sharedPreferences.edit()
            .remove(ACCESS_TOKEN_KEY)
            .remove(REFRESH_TOKEN_KEY)
            .apply()
    }
}