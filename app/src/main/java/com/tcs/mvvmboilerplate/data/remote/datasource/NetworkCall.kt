package com.tcs.mvvmboilerplate.data.remote.datasource

import com.tcs.mvvmboilerplate.data.remote.NetworkConnectivity
import retrofit2.Response

object NetworkCall {
    suspend fun networkCallWithoutStatusCode(
        responseCall: suspend () -> Response<*>,
        networkConnectivity: NetworkConnectivity
    ): Any? {
        if (!networkConnectivity.isConnected()) {
            return "NO_INTERNET_CONNECTION"
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: Exception) {
            return e.message.toString()
        }
    }

    suspend fun networkCallWithStatusCode(
        responseCall: suspend () -> Response<*>,
        networkConnectivity: NetworkConnectivity
    ): Any {
        if (!networkConnectivity.isConnected()) {
            return "NO_INTERNET_CONNECTION"
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                Pair(response.body(), responseCode)
            } else {
                responseCode
            }
        } catch (e: Exception) {
            return e.message.toString()
        }
    }

}
