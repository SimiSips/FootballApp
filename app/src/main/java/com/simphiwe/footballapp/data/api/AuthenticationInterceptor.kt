package com.simphiwe.footballapp.data.api

import com.simphiwe.footballapp.constants.constants.RapidAPI_Host
import com.simphiwe.footballapp.constants.constants.RapidAPI_Key
import com.simphiwe.footballapp.constants.constants.X_RapidAPI_Host
import com.simphiwe.footballapp.constants.constants.X_RapidAPI_Key
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .addHeader(RapidAPI_Key, X_RapidAPI_Key)
            .addHeader(RapidAPI_Host, X_RapidAPI_Host)
            .build()

        return chain.proceed(newRequest)
    }
}