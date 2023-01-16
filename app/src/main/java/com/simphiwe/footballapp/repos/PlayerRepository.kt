package com.simphiwe.footballapp.repos

import com.simphiwe.footballapp.data.api.PlayerApi
import javax.inject.Inject

class PlayerRepository @Inject constructor(private val api: PlayerApi){

    suspend fun getAllPlayers() = api.getAllPlayers()

}
