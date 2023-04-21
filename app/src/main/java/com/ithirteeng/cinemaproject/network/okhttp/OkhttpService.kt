package com.ithirteeng.cinemaproject.network.okhttp


import com.ithirteeng.cinemaproject.network.authenticator.TokenAuthenticator
import com.ithirteeng.cinemaproject.network.interceptor.AuthInterceptor
import com.ithirteeng.cinemaproject.network.interceptor.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

fun setupTokenOkHttpClient(
    tokenAuthenticator: TokenAuthenticator,
    authInterceptor: AuthInterceptor,
    loggingInterceptor: HttpLoggingInterceptor,
    networkConnectionInterceptor: NetworkConnectionInterceptor,
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(networkConnectionInterceptor)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .authenticator(tokenAuthenticator)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()

fun setupOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    networkConnectionInterceptor: NetworkConnectionInterceptor
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(networkConnectionInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()