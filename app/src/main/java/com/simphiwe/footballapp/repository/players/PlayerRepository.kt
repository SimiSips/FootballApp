package com.simphiwe.footballapp.repository.players

import com.simphiwe.footballapp.common.Resource
import com.simphiwe.footballapp.data.model.player.PlayersResponse
import com.simphiwe.footballapp.data.model.topscorers.TopScorersResponse

interface PlayerRepository {

    suspend fun getPlayers(team: Int, season: Int): Resource<PlayersResponse>

    suspend fun getTopScorers(league: Int, season: Int): Resource<TopScorersResponse>

}