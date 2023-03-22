package com.ithirteeng.cinemaproject.network.interceptor

import com.ithirteeng.shared.network.common.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return try {
            chain.proceed(request)
        } catch (e: IOException) {
            handleError()
        }
    }

    private fun handleError(): Nothing {
        throw NoConnectivityException()
    }


}