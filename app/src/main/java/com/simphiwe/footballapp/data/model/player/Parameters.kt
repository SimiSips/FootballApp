package com.simphiwe.footballapp.data.model.player


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("id")
    val id: String,
    @SerializedName("season")
    val season: String
)