package com.simphiwe.footballapp.view.fragment.topscorers.model


import com.google.gson.annotations.SerializedName

data class Duels(
    @SerializedName("total")
    val total: Int,
    @SerializedName("won")
    val won: Int
)