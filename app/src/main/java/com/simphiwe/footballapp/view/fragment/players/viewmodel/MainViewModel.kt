package com.simphiwe.footballapp.view.fragment.players.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simphiwe.footballapp.di.RetroInstance
import com.simphiwe.footballapp.di.RetroService
import com.simphiwe.footballapp.model.PlayerList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel : ViewModel(){

    var playerListLiveData: MutableLiveData<PlayerList> = MutableLiveData<PlayerList>()


    init {
        playerListLiveData = MutableLiveData()
    }

    fun getPlayerListObserver(): MutableLiveData<PlayerList>{
        return playerListLiveData
    }


    fun makeApiCall() = viewModelScope.launch(Dispatchers.IO) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val response = retroInstance.getDataFromApi(33, 2022)
        playerListLiveData.postValue(response)
    }

}
