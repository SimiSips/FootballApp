package com.simphiwe.footballapp.data.model.teams


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("search")
    val search: String
)