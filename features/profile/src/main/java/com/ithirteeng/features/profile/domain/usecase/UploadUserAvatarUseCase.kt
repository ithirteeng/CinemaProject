package com.ithirteeng.features.profile.domain.usecase

import android.net.Uri
import com.ithirteeng.features.profile.domain.repository.ProfileRepository

class UploadUserAvatarUseCase(
    private val repository: ProfileRepository,
) {
    suspend operator fun invoke(uri: Uri) =
        repository.changeUsersAvatar(uri)
}