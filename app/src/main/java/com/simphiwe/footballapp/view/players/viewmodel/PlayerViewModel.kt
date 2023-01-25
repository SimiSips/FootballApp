package com.simphiwe.footballapp.view.players.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simphiwe.footballapp.common.UIState
import com.simphiwe.footballapp.repository.players.PlayerRepository
import com.simphiwe.footballapp.data.model.player.PlayersResponse
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
            println(getPlayerList)

            try {
                //emit(Resource.Loading())
                playersViewState.postValue(UIState.Loading)
                val result = playerRepository.getPlayers(team, season)

                playersViewState.postValue(UIState.Success(result.data))

            } catch (e: HttpException) {
                playersViewState.postValue(UIState.Failure(e.localizedMessage ?: "An unexpected error occurred"))

            } catch (e: IOException) {
                playersViewState.postValue(UIState.Failure(e.localizedMessage ?: "Could not reach server. Please check your internet connection."))

            }
        }


    }

}
