package com.simphiwe.footballapp.data.model.player


import com.google.gson.annotations.SerializedName

data class Dribbles(
    @SerializedName("attempts")
    val attempts: Int,
    @SerializedName("past")
    val past: Any,
    @SerializedName("success")
    val success: Int
)