package com.simphiwe.footballapp.view.fragment.teams.model


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("code")
    val code: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("name")
    val name: String
)