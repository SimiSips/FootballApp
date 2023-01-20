package com.simphiwe.footballapp.view.fragment.topscorers.model


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("league")
    val league: String,
    @SerializedName("season")
    val season: String
)