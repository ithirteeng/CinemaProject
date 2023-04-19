package com.ithirteeng.features.chat.di

import com.ithirteeng.features.chat.data.api.ChatWebSocket
import com.ithirteeng.features.chat.data.api.ChatWebSocketListener
import com.ithirteeng.features.chat.data.datasource.ChatRemoteDatasource
import com.ithirteeng.features.chat.data.datasource.ChatRemoteDatasourceImpl
import com.ithirteeng.features.chat.data.repository.ChatRepositoryImpl
import com.ithirteeng.features.chat.domain.repository.ChatRepository
import com.ithirteeng.features.chat.domain.usecase.CloseSocketUseCase
import com.ithirteeng.features.chat.domain.usecase.GetMessagesFlowUseCase
import com.ithirteeng.features.chat.domain.usecase.InitSocketUseCase
import com.ithirteeng.features.chat.domain.usecase.SendMessageUseCase
import com.ithirteeng.features.chat.presentation.ChatFragmentViewModel
import com.ithirteeng.shared.network.common.TOKEN_NETWORK_TOOLS
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val chatModule = module {

    single { ChatWebSocketListener() }
    single {
        ChatWebSocket(
            okHttpClient = get(named(TOKEN_NETWORK_TOOLS)),
            webSocketListener = get()
        )
    }

    factory<ChatRemoteDatasource> { ChatRemoteDatasourceImpl(socket = get()) }
    factory<ChatRepository> { ChatRepositoryImpl(remoteDatasource = get()) }

    factory { GetMessagesFlowUseCase(repository = get()) }
    factory { SendMessageUseCase(repository = get()) }
    factory { CloseSocketUseCase(repository = get()) }
    factory { InitSocketUseCase(repository = get()) }

    viewModel {
        ChatFragmentViewModel(
            router = get()
        )
    }
}