package com.simphiwe.footballapp.data.model.topscorers


import com.google.gson.annotations.SerializedName

data class Passes(
    @SerializedName("accuracy")
    val accuracy: Int,
    @SerializedName("key")
    val key: Int,
    @SerializedName("total")
    val total: Int
)