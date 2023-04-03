package com.ithirteeng.features.episode.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.features.episode.domain.usecase.*

class EpisodeFragmentViewModel(
    private val getEpisodeTimeUseCase: GetEpisodeTimeUseCase,
    private val getEpisodesListUseCase: GetEpisodesListUseCase,
    private val getEpisodeUseCase: GetEpisodeUseCase,
    private val setEpisodeTimeUseCase: SetEpisodeTimeUseCase,
    private val getEpisodesYearsUseCase: GetEpisodesYearsUseCase,
    private val router: EpisodeRouter
) : ViewModel() {
}