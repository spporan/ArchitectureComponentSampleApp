package com.poran.architecturecomponentsampleapp.data.api

import android.content.Context
import android.net.ConnectivityManager
import com.poran.architecturecomponentsampleapp.utils.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor (
    private val context:Context
):Interceptor{
    private val applicationContext=context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isInternetAvailable())
            throw NoNetworkException("No Internet Connection")

        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable():Boolean{
        val connectivityManager=applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return  it!=null && it.isConnected
        }
    }
}