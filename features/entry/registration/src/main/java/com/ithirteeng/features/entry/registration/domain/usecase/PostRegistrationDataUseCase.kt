package com.ithirteeng.features.entry.registration.domain.usecase

import com.ithirteeng.features.entry.registration.domain.entity.RegistrationEntity
import com.ithirteeng.features.entry.registration.domain.repository.RegistrationRepository
import com.ithirteeng.shared.token.domain.entity.TokenEntity

class PostRegistrationDataUseCase(
    private val repository: RegistrationRepository
) {
    suspend operator fun invoke(registrationEntity: RegistrationEntity): Result<TokenEntity> =
        repository.postRegistrationData(registrationEntity)
}