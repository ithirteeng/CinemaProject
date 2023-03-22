package com.ithirteeng.shared.token.di

import com.ithirteeng.shared.network.common.SIMP_NETWORK_TOOLS
import com.ithirteeng.shared.network.retrofitservice.createRetrofitService
import com.ithirteeng.shared.token.data.api.TokenApi
import com.ithirteeng.shared.token.data.datasource.LocalTokenDatasource
import com.ithirteeng.shared.token.data.datasource.LocalTokenDatasourceImpl
import com.ithirteeng.shared.token.data.datasource.RemoteTokenDatasource
import com.ithirteeng.shared.token.data.datasource.RemoteTokenDatasourceImpl
import com.ithirteeng.shared.token.data.repository.TokenRepositoryImpl
import com.ithirteeng.shared.token.data.storage.TokenStorage
import com.ithirteeng.shared.token.domain.repository.TokenRepository
import com.ithirteeng.shared.token.domain.usecase.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

val tokenModule = module {
    single { createRetrofitService<TokenApi>(get(named(SIMP_NETWORK_TOOLS))) }

    single { TokenStorage(context = get()) }

    factory<RemoteTokenDatasource> { RemoteTokenDatasourceImpl(api = get()) }
    factory<LocalTokenDatasource> { LocalTokenDatasourceImpl(storage = get()) }

    factory<TokenRepository> {
        TokenRepositoryImpl(
            localDatasource = get(),
            remoteDatasource = get()
        )
    }

    factory { SaveTokenToLocalStorageUseCase(repository = get()) }
    factory { GetTokenFromLocalStorageUseCase(repository = get()) }
    factory { RefreshTokenUseCase(repository = get()) }
    factory { CheckTokenExistenceUseCase(repository = get()) }
    factory { RemoveTokenFromLocalStorageUseCase(repository = get()) }
}