package com.ithirteeng.features.discussions.di

import com.ithirteeng.features.discussions.data.api.DiscussionsApi
import com.ithirteeng.features.discussions.data.datasource.DiscussionsRemoteDatasource
import com.ithirteeng.features.discussions.data.datasource.DiscussionsRemoteDatasourceImpl
import com.ithirteeng.features.discussions.data.repository.DiscussionsRepositoryImpl
import com.ithirteeng.features.discussions.domain.repository.DiscussionsRepository
import com.ithirteeng.features.discussions.domain.usecase.GetChatsListUseCase
import com.ithirteeng.features.discussions.presentation.DiscussionsFragmentViewModel
import com.ithirteeng.shared.network.common.TOKEN_NETWORK_TOOLS
import com.ithirteeng.shared.network.retrofitservice.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val discussionsModule = module {
    single { createRetrofitService<DiscussionsApi>(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }

    factory<DiscussionsRemoteDatasource> { DiscussionsRemoteDatasourceImpl(api = get()) }
    factory<DiscussionsRepository> { DiscussionsRepositoryImpl(remoteDatasource = get()) }

    factory { GetChatsListUseCase(repository = get()) }

    viewModel {
        DiscussionsFragmentViewModel(
            getChatsListUseCase = get(),
            router = get()
        )
    }
}