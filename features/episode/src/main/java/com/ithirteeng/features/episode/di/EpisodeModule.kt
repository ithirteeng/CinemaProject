package com.ithirteeng.features.episode.di

import com.ithirteeng.features.episode.data.api.EpisodeApi
import com.ithirteeng.features.episode.data.datasource.EpisodeRemoteDatasource
import com.ithirteeng.features.episode.data.datasource.EpisodeRemoteDatasourceImpl
import com.ithirteeng.features.episode.data.repository.EpisodeRepositoryImpl
import com.ithirteeng.features.episode.domain.repository.EpisodeRepository
import com.ithirteeng.features.episode.domain.usecase.GetEpisodeTimeUseCase
import com.ithirteeng.features.episode.domain.usecase.GetEpisodeUseCase
import com.ithirteeng.features.episode.domain.usecase.GetEpisodesListUseCase
import com.ithirteeng.features.episode.domain.usecase.SetEpisodeTimeUseCase
import com.ithirteeng.shared.network.common.TOKEN_NETWORK_TOOLS
import com.ithirteeng.shared.network.retrofitservice.createRetrofitService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val episodeModule = module {
    single { createRetrofitService<EpisodeApi>(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }

    factory<EpisodeRemoteDatasource> { EpisodeRemoteDatasourceImpl(api = get()) }
    factory<EpisodeRepository> { EpisodeRepositoryImpl(remoteDatasource = get()) }

    factory { GetEpisodeUseCase(repository = get()) }
    factory { GetEpisodesListUseCase(repository = get()) }
    factory { GetEpisodeTimeUseCase(repository = get()) }
    factory { SetEpisodeTimeUseCase(repository = get()) }
}