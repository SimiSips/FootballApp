package com.simphiwe.footballapp.repos

import com.simphiwe.footballapp.common.Resource
import com.simphiwe.footballapp.view.fragment.players.model.PlayersResponse
import com.simphiwe.footballapp.view.fragment.topscorers.model.TopScorersResponse

interface PlayerRepository {

    suspend fun getPlayers(team: Int, season: Int): Resource<PlayersResponse>

    suspend fun getTopScorers(league: Int, season: Int): Resource<TopScorersResponse>

}