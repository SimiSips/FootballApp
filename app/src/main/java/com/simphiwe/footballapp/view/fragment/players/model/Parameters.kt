package com.simphiwe.footballapp.view.fragment.players.model


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("id")
    val id: String,
    @SerializedName("season")
    val season: String
)