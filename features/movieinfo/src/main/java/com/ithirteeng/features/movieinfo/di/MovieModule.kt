package com.ithirteeng.features.movieinfo.di

import com.ithirteeng.features.movieinfo.data.api.MovieApi
import com.ithirteeng.features.movieinfo.data.datasource.MovieRemoteDatasource
import com.ithirteeng.features.movieinfo.data.datasource.MovieRemoteDatasourceImpl
import com.ithirteeng.features.movieinfo.data.repository.MovieRepositoryImpl
import com.ithirteeng.features.movieinfo.domain.repository.MovieRepository
import com.ithirteeng.features.movieinfo.domain.usecase.GetMovieEpisodesListUseCase
import com.ithirteeng.features.movieinfo.presentation.MovieFragmentViewModel
import com.ithirteeng.shared.network.common.TOKEN_NETWORK_TOOLS
import com.ithirteeng.shared.network.retrofitservice.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieModule = module {
    single { createRetrofitService<MovieApi>(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }

    factory<MovieRemoteDatasource> { MovieRemoteDatasourceImpl(api = get()) }
    factory<MovieRepository> { MovieRepositoryImpl(remoteDatasource = get()) }

    factory { GetMovieEpisodesListUseCase(repository = get()) }

    viewModel {
        MovieFragmentViewModel(
            application = get(),
            getMovieEpisodesListUseCase = get(),
            router = get()
        )
    }
}