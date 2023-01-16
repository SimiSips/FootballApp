package com.simphiwe.footballapp.model

data class PlayerList(val response: ArrayList<PlayerData>)
data class PlayerData(val name: String, val age: Int, val nationality: String, val photo: String)
//data class PlayerData(val name: String, val pastor: String)