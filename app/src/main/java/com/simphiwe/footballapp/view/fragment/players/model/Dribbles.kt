package com.simphiwe.footballapp.view.fragment.players.model


import com.google.gson.annotations.SerializedName

data class Dribbles(
    @SerializedName("attempts")
    val attempts: Int,
    @SerializedName("past")
    val past: Any,
    @SerializedName("success")
    val success: Int
)