package com.simphiwe.footballapp.di

import com.simphiwe.footballapp.model.PlayerData
import com.simphiwe.footballapp.model.PlayerList
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface RetroService {

    @Headers(
        "X-RapidAPI-Key: 51b9ca8d21msh55585eace5b817ep148b67jsnf4df22b06cdb",
    )
    @GET("players")
    suspend fun getDataFromApi(@Query("id") id: Int, @Query("season") season: Int): PlayerList
}