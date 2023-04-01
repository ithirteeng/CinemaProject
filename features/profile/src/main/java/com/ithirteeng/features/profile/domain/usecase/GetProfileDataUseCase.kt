package com.ithirteeng.features.profile.domain.usecase

import com.ithirteeng.features.profile.domain.entity.ProfileEntity
import com.ithirteeng.features.profile.domain.repository.ProfileRepository

class GetProfileDataUseCase(
    private val repository: ProfileRepository,
) {
    suspend operator fun invoke(): Result<ProfileEntity> =
        repository.getProfileData()
}