package com.simphiwe.footballapp.repos

import com.simphiwe.footballapp.common.Resource
import com.simphiwe.footballapp.view.fragment.teams.model.SearchResponse
import com.simphiwe.footballapp.view.fragment.teams.teamslist.model.TeamsResponse
import dagger.Provides

interface TeamsRepository {

    suspend fun getCountry(country: String): Resource<SearchResponse>

    suspend fun getTeams(search: String): Resource<TeamsResponse>
}