package com.simphiwe.footballapp.data.api

import com.simphiwe.footballapp.data.model.player.PlayersResponse
import com.simphiwe.footballapp.data.model.search.SearchResponse
import com.simphiwe.footballapp.data.model.teams.TeamsResponse
import com.simphiwe.footballapp.data.model.topscorers.TopScorersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayerApi {

    @GET("players")
    suspend fun getPlayers(
        @Query("team") team: Int,
        @Query("season") season: Int
    ): Response<PlayersResponse>

    @GET("players/topscorers")
    suspend fun getTopScorers(
        @Query("league") league: Int,
        @Query("season") season: Int
    ): Response<TopScorersResponse>

    @GET("countries")
    suspend fun getCountry(
        @Query("search") team: String
    ): Response<SearchResponse>

    @GET("teams")
    suspend fun getTeams(
        @Query("search") search: String
    ): Response<TeamsResponse>
}