package com.ithirteeng.features.profile.domain.usecase

import com.ithirteeng.features.profile.domain.repository.ProfileRepository

class UploadUserAvatarUseCase(
    private val repository: ProfileRepository,
) {
    suspend operator fun invoke(byteArray: ByteArray): Result<Unit> =
        repository.changeUsersAvatar(byteArray)
}