package com.simphiwe.footballapp.data.model.topscorers


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("player")
    val player: Player,
    @SerializedName("statistics")
    val statistics: List<Statistic>
)