package com.simphiwe.footballapp.repos

import com.simphiwe.footballapp.common.Resource
import com.simphiwe.footballapp.data.api.PlayerApi
import com.simphiwe.footballapp.view.fragment.players.model.PlayersResponse
import com.simphiwe.footballapp.view.fragment.topscorers.model.TopScorersResponse
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