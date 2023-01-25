package com.simphiwe.footballapp.repository.players

import com.simphiwe.footballapp.common.Resource
import com.simphiwe.footballapp.data.api.PlayerApi
import com.simphiwe.footballapp.data.model.player.PlayersResponse
import com.simphiwe.footballapp.data.model.topscorers.TopScorersResponse
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(private val api: PlayerApi) : PlayerRepository {

    override suspend fun getPlayers(team: Int, season: Int): Resource<PlayersResponse> {
        return try {
            val response = api.getPlayers(team, season)
            val result = response.body()
            if(response.isSuccessful && result!=null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e : Exception) {
            Resource.Error(e.message?: "An error occurred while getting characters")
        }
    }

    override suspend fun getTopScorers(league: Int, season: Int): Resource<TopScorersResponse> {
        return try {
            val response = api.getTopScorers(league, season)
            val result = response.body()
            if(response.isSuccessful && result!=null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e : Exception) {
            Resource.Error(e.message?: "An error occurred while getting characters")
        }
    }
}