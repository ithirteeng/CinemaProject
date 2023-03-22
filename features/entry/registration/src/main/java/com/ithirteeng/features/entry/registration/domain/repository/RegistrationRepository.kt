package com.ithirteeng.features.entry.registration.domain.repository

import com.ithirteeng.features.entry.registration.domain.entity.RegistrationEntity
import com.ithirteeng.shared.token.domain.entity.TokenEntity

interface RegistrationRepository {
    suspend fun postRegistrationData(registrationEntity: RegistrationEntity): Result<TokenEntity>
}