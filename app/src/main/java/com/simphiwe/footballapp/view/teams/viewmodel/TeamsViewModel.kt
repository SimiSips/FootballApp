package com.simphiwe.footballapp.view.teams.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simphiwe.footballapp.common.UIState
import com.simphiwe.footballapp.repository.teams.TeamsRepository
import com.simphiwe.footballapp.data.model.search.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val teamsRepository: TeamsRepository
) : ViewModel(){

    private val countryList = MutableLiveData<SearchResponse>()

    val getCountryList: LiveData<SearchResponse> = countryList


    private val teamsViewState = MutableLiveData<UIState>()

    val getTeamsViewState : LiveData<UIState> = teamsViewState


    fun fetchTeams(search: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (search.isNullOrBlank()){
                teamsViewState.postValue(UIState.Failure("Please enter something in the search bar"))
            } else{
                val result = teamsRepository.getTeams(search)
                result.data

                try {
                    //emit(Resource.Loading())
                    teamsViewState.postValue(UIState.Loading)
                    val result = teamsRepository.getTeams(search)
                    //playerList.postValue(result.data!!)
                    teamsViewState.postValue(UIState.Success(result.data))
                    //emit(Resource.Success(players.data))
                } catch (e: HttpException) {
                    teamsViewState.postValue(UIState.Failure(e.localizedMessage ?: "An unexpected error occurred"))
                    //emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
                } catch (e: IOException) {
                    teamsViewState.postValue(UIState.Failure(e.localizedMessage ?: "Could not reach server. Please check your internet connection."))
                    //emit(Resource.Error("Could not reach server. Please check your internet connection."))
                }
            }

        }



    }

}