package com.simphiwe.footballapp.view.topscorers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simphiwe.footballapp.common.UIState
import com.simphiwe.footballapp.repository.players.PlayerRepository
import com.simphiwe.footballapp.data.model.topscorers.TopScorersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class TopScorersViewModel @Inject constructor(private val playerRepository: PlayerRepository) : ViewModel(){

    private val topScorersList = MutableLiveData<TopScorersResponse>()

    val getPlayerList: LiveData<TopScorersResponse> = topScorersList


    private val topScorersViewState = MutableLiveData<UIState>()

    val getTopScorersViewState : LiveData<UIState> = topScorersViewState



    /*fun getPlayerListObserver(): LiveData<TopScorersResponse> {
        return getPlayerList
    }*/


    fun fetchTopScorers(league: Int, season: Int) {
        viewModelScope.launch(Dispatchers.IO) {
//            val result = playerRepository.getPlayers(1, 2022)
//            result.data

            try {
                //emit(Resource.Loading())
                topScorersViewState.postValue(UIState.Loading)
                val result = playerRepository.getTopScorers(league, season)
                //playerList.postValue(result.data!!)
                topScorersViewState.postValue(UIState.Success(result.data))
                //emit(Resource.Success(players.data))
            } catch (e: HttpException) {
                topScorersViewState.postValue(UIState.Failure(e.localizedMessage ?: "An unexpected error occurred"))
                //emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                topScorersViewState.postValue(UIState.Failure(e.localizedMessage ?: "Could not reach server. Please check your internet connection."))
                //emit(Resource.Error("Could not reach server. Please check your internet connection."))
            }
        }


    }

}