package com.simphiwe.footballapp.data.model.topscorers


import com.google.gson.annotations.SerializedName

data class Birth(
    @SerializedName("country")
    val country: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("place")
    val place: String
)