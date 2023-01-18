package com.simphiwe.footballapp.view.fragment.players.model


import com.google.gson.annotations.SerializedName

data class Tackles(
    @SerializedName("blocks")
    val blocks: Int,
    @SerializedName("interceptions")
    val interceptions: Int,
    @SerializedName("total")
    val total: Int
)