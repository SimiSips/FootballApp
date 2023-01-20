package com.simphiwe.footballapp.view.fragment.players.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simphiwe.footballapp.common.UIState
import com.simphiwe.footballapp.repos.PlayerRepository
import com.simphiwe.footballapp.view.fragment.players.model.PlayersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(private val playerRepository: PlayerRepository) : ViewModel(){

    private val playerList = MutableLiveData<PlayersResponse>()

    val getPlayerList: LiveData<PlayersResponse> = playerList


    private val playersViewState = MutableLiveData<UIState>()

    val getPlayerViewState : LiveData<UIState> = playersViewState



    fun getPlayerListObserver(): LiveData<PlayersResponse> {
        return getPlayerList
    }


    fun fetchPlayers(team: Int, season: Int) {
        viewModelScope.launch(Dispatchers.IO) {
//            val result = playerRepository.getPlayers(1, 2022)
//            result.data

            try {
                //emit(Resource.Loading())
                playersViewState.postValue(UIState.Loading)
                val result = playerRepository.getPlayers(team, season)
                //playerList.postValue(result.data!!)
                playersViewState.postValue(UIState.Success(result.data))
                //emit(Resource.Success(players.data))
            } catch (e: HttpException) {
                playersViewState.postValue(UIState.Failure(e.localizedMessage ?: "An unexpected error occurred"))
                //emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                playersViewState.postValue(UIState.Failure(e.localizedMessage ?: "Could not reach server. Please check your internet connection."))
                //emit(Resource.Error("Could not reach server. Please check your internet connection."))
            }
        }


    }

}
