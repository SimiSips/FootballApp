package com.simphiwe.footballapp.data.model.player


import com.google.gson.annotations.SerializedName

data class Goals(
    @SerializedName("assists")
    val assists: Any,
    @SerializedName("conceded")
    val conceded: Int,
    @SerializedName("saves")
    val saves: Any,
    @SerializedName("total")
    val total: Int
)