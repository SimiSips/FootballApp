package com.simphiwe.footballapp.repository.teams

import com.simphiwe.footballapp.common.Resource
import com.simphiwe.footballapp.data.model.search.SearchResponse
import com.simphiwe.footballapp.data.model.teams.TeamsResponse

interface TeamsRepository {

    suspend fun getCountry(country: String): Resource<SearchResponse>

    suspend fun getTeams(search: String): Resource<TeamsResponse>
}