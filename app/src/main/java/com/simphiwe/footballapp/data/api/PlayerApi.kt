package com.simphiwe.footballapp.data.api

import com.simphiwe.footballapp.model.Player
import com.simphiwe.footballapp.view.fragment.players.model.PlayersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayerApi {

    @GET("players")
    suspend fun getPlayers(
        @Query("id") id: Int,
        @Query("season") season: Int
    ): Response<PlayersResponse>
}