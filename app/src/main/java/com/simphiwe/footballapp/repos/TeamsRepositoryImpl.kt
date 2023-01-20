package com.simphiwe.footballapp.repos

import com.simphiwe.footballapp.common.Resource
import com.simphiwe.footballapp.data.api.PlayerApi
import com.simphiwe.footballapp.view.fragment.teams.model.SearchResponse
import com.simphiwe.footballapp.view.fragment.teams.teamslist.model.TeamsResponse
import javax.inject.Inject

class TeamsRepositoryImpl @Inject constructor(private val api: PlayerApi) : TeamsRepository {

    override suspend fun getCountry(country: String): Resource<SearchResponse> {
        return try {
            val response = api.getCountry(country)
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

    override suspend fun getTeams(search: String): Resource<TeamsResponse> {
        return try {
            val response = api.getTeams(search)
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