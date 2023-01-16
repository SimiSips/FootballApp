package com.simphiwe.footballapp.di

import retrofit2.Retrofit
import com.simphiwe.footballapp.constants.constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object {

//        val okHttpClient = OkHttpClient().newBuilder()
//            .addInterceptor { chain ->
//                val request = chain.request()
//                    .newBuilder()
//                    .addHeader("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
//                    .addHeader("x-rapidapi-key", "51b9ca8d21msh55585eace5b817ep148b67jsnf4df22b06cdb")
//                    .build()
//                chain.proceed(request)
//            }
//            .build()

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}