package com.simphiwe.footballapp.data.model.topscorers


import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("country")
    val country: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("season")
    val season: Int
)