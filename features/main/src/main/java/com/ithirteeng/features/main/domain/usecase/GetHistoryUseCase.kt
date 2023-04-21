package com.ithirteeng.features.main.domain.usecase

import com.ithirteeng.features.main.domain.repository.MainRepository
import com.ithirteeng.shared.movies.entity.EpisodeViewEntity

class GetHistoryUseCase(
    private val repository: MainRepository,
) {
    suspend operator fun invoke(): Result<List<EpisodeViewEntity>> =
        repository.getHistory()
}