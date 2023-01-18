package com.simphiwe.footballapp.view.fragment.players.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simphiwe.footballapp.model.PlayerList
import com.simphiwe.footballapp.repos.PlayerRepository
import com.simphiwe.footballapp.view.fragment.players.model.Player
import com.simphiwe.footballapp.view.fragment.players.model.PlayersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class PlayerViewModel @Inject constructor(private val playerRepository: PlayerRepository) : ViewModel(){

    private val playerList = MutableLiveData<PlayersResponse>()

    val getPlayerList: LiveData<PlayersResponse> = playerList


    //var playerListLiveData: MutableLiveData<Player> = MutableLiveData<Player>()



    /*init {
        playerListLiveData = MutableLiveData()
    }*/

    fun getPlayerListObserver(): LiveData<PlayersResponse> {
        return getPlayerList
    }


    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = playerRepository.getPlayers(276, 2020)
            result.data
        }
    }

}
