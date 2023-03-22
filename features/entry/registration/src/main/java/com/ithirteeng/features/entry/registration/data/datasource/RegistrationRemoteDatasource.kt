package com.ithirteeng.features.entry.registration.data.datasource

import com.ithirteeng.features.entry.registration.domain.entity.RegistrationEntity
import com.ithirteeng.shared.token.domain.entity.TokenEntity

interface RegistrationRemoteDatasource {
    suspend fun postRegistrationData(registrationEntity: RegistrationEntity): TokenEntity
}