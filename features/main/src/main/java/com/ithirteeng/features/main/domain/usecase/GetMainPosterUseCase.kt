package com.ithirteeng.features.main.domain.usecase

import com.ithirteeng.features.main.domain.entity.PosterEntity
import com.ithirteeng.features.main.domain.repository.MainRepository

class GetMainPosterUseCase(
    private val repository: MainRepository,
) {
    suspend operator fun invoke(): Result<PosterEntity> =
        repository.getMainPoster()
}