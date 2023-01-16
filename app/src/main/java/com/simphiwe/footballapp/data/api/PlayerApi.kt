package com.simphiwe.footballapp.data.api

import com.simphiwe.footballapp.model.Player
import retrofit2.Response
import retrofit2.http.GET

interface PlayerApi {

    @GET("players/")
    suspend fun getAllPlayers() : Response<List<Player>>
}