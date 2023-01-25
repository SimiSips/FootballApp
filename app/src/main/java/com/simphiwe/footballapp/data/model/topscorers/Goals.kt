package com.simphiwe.footballapp.data.model.topscorers


import com.google.gson.annotations.SerializedName

data class Goals(
    @SerializedName("assists")
    val assists: Int,
    @SerializedName("conceded")
    val conceded: Int,
    @SerializedName("saves")
    val saves: Any,
    @SerializedName("total")
    val total: Int
)