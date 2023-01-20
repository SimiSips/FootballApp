package com.simphiwe.footballapp.view.fragment.teams.model


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("search")
    val search: String
)