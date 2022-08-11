package com.errorclient.filmlist.data.repository.usecase

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class InternetAvailable(private val context: Context) {

    fun execute(): Boolean {
        val result: Boolean
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNow =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        result = when {
            activeNow.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNow.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNow.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

        return result
    }
}