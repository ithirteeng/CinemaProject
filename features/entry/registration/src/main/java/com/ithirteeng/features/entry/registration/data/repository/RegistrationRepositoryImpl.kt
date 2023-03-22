package com.ithirteeng.features.entry.registration.data.repository

import com.ithirteeng.features.entry.registration.data.datasource.RegistrationRemoteDatasource
import com.ithirteeng.features.entry.registration.domain.entity.RegistrationEntity
import com.ithirteeng.features.entry.registration.domain.repository.RegistrationRepository
import com.ithirteeng.shared.token.domain.entity.TokenEntity

class RegistrationRepositoryImpl(
    private val remoteDatasource: RegistrationRemoteDatasource
) : RegistrationRepository {
    override suspend fun postRegistrationData(registrationEntity: RegistrationEntity): Result<TokenEntity> {
        return try {
            Result.success(remoteDatasource.postRegistrationData(registrationEntity))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}