package com.simphiwe.footballapp.data.model.topscorers


import com.google.gson.annotations.SerializedName

data class Shots(
    @SerializedName("on")
    val on: Int,
    @SerializedName("total")
    val total: Int
)