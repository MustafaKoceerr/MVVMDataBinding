package com.kocerlabs.mvvmdatabinding.data.network.model

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.kocerlabs.mvvmdatabinding.util.NoInternetException
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor(
    @ApplicationContext private val context: Context // Context'i property olarak tanımlıyoruz
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable(context)) {
            throw NoInternetException("Make sure you have an active data connection")
        }
        return chain.proceed(chain.request())
    }


    /**
     * Cihazımızda internet bağlantısı olup olmadığını kontrol etmek için,
     * context'e ihtiyacımız var. Constructor'dan alıyoruz.
     */

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }


}