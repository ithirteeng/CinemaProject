package com.ithirteeng.features.main.di

import com.ithirteeng.features.main.data.api.MainApi
import com.ithirteeng.features.main.data.datasource.MainRemoteDatasource
import com.ithirteeng.features.main.data.datasource.MainRemoteDatasourceImpl
import com.ithirteeng.features.main.data.repository.MainRepositoryImpl
import com.ithirteeng.features.main.domain.repository.MainRepository
import com.ithirteeng.features.main.domain.usecase.GetHistoryUseCase
import com.ithirteeng.features.main.domain.usecase.GetMainPosterUseCase
import com.ithirteeng.features.main.domain.usecase.GetMovieEpisodesListUseCase
import com.ithirteeng.features.main.domain.usecase.GetMoviesListUseCase
import com.ithirteeng.features.main.presentation.MainFragmentViewModel
import com.ithirteeng.shared.network.common.TOKEN_NETWORK_TOOLS
import com.ithirteeng.shared.network.retrofitservice.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {
    single { createRetrofitService<MainApi>(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }

    factory<MainRemoteDatasource> { MainRemoteDatasourceImpl(api = get()) }
    factory<MainRepository> { MainRepositoryImpl(remoteDatasource = get()) }

    factory { GetMoviesListUseCase(repository = get()) }
    factory { GetMovieEpisodesListUseCase(repository = get()) }
    factory { GetHistoryUseCase(repository = get()) }
    factory { GetMainPosterUseCase(repository = get()) }

    viewModel {
        MainFragmentViewModel(
            application = get(),
            router = get(),
            getHistoryUseCase = get(),
            getMovieEpisodesListUseCase = get(),
            getMoviesListUseCase = get(),
            getMainPosterUseCase = get()
        )
    }
}