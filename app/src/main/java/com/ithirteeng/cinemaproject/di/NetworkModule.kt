package com.ithirteeng.cinemaproject.di

import com.ithirteeng.cinemaproject.network.authenticator.TokenAuthenticator
import com.ithirteeng.cinemaproject.network.interceptor.AuthInterceptor
import com.ithirteeng.cinemaproject.network.okhttp.setupOkHttpClient
import com.ithirteeng.cinemaproject.network.okhttp.setupTokenOkHttpClient
import com.ithirteeng.cinemaproject.network.provideLoggingInterceptor
import com.ithirteeng.cinemaproject.network.provideNetworkConnectionInterceptor
import com.ithirteeng.cinemaproject.network.provideRetrofit
import com.ithirteeng.shared.network.common.SIMP_NETWORK_TOOLS
import com.ithirteeng.shared.network.common.TOKEN_NETWORK_TOOLS
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    factory { provideLoggingInterceptor() }
    factory { provideNetworkConnectionInterceptor() }

    single(named(SIMP_NETWORK_TOOLS)) {
        provideRetrofit(okHttpClient = get(named(SIMP_NETWORK_TOOLS)))
    }

    single(named(TOKEN_NETWORK_TOOLS)) {
        provideRetrofit(okHttpClient = get(named(TOKEN_NETWORK_TOOLS)))
    }

    single(named(SIMP_NETWORK_TOOLS)) {
        setupOkHttpClient(
            loggingInterceptor = get(),
            networkConnectionInterceptor = get()
        )
    }

    factory(named(TOKEN_NETWORK_TOOLS)) {
        setupTokenOkHttpClient(
            loggingInterceptor = get(),
            networkConnectionInterceptor = get(),
            authInterceptor = get(),
            tokenAuthenticator = get()
        )
    }

    factory {
        TokenAuthenticator(
            getTokenFromLocalStorageUseCase = get(),
            saveTokenToLocalStorageUseCase = get(),
            refreshTokenUseCase = get()
        )
    }

    factory {
        AuthInterceptor(
            getTokenFromLocalStorageUseCase = get()
        )
    }


}