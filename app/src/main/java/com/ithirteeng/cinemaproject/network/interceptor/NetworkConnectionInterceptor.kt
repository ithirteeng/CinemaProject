package com.ithirteeng.cinemaproject.network.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.ithirteeng.shared.network.common.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class NetworkConnectionInterceptor(
    private val context: Context,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()


        return try {
            chain.proceed(request)
        } catch (e: IOException) {
            if (isNetworkAvailable() || e is java.net.ConnectException) {
                chain.proceed(request)
            } else {
                handleError()
            }
        }
    }

    private fun handleError(): Nothing {
        throw NoConnectivityException()
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }


}