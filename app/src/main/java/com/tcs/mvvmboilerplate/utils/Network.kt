package com.tcs.mvvmboilerplate.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class Network @Inject constructor(val context: Context) : NetworkConnectivity {
    override fun getNetworkInfo(): NetworkCapabilities? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = cm.activeNetwork
        return cm.getNetworkCapabilities(nw)

    }

    override fun isConnected(): Boolean {
        val info = getNetworkInfo()

        return when {
            info?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> true
            info?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> true
            //for other device how are able to connect with Ethernet
            info?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true -> true
            //for check internet over Bluetooth
            info?.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) == true -> true
            else -> false
        }
    }
}

interface NetworkConnectivity {
    fun getNetworkInfo(): NetworkCapabilities?
    fun isConnected(): Boolean
}