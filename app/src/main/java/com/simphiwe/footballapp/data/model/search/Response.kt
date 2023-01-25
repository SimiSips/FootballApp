package com.simphiwe.footballapp.data.model.search


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("code")
    val code: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("name")
    val name: String
)