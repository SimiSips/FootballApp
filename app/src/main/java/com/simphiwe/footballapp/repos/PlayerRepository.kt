package com.simphiwe.footballapp.repos

import com.simphiwe.footballapp.common.Resource
import com.simphiwe.footballapp.view.fragment.players.model.PlayersResponse

interface PlayerRepository {

    suspend fun getPlayers(id: Int, season: Int): Resource<PlayersResponse>

}