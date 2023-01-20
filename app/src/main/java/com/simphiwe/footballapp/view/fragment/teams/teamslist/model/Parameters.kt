package com.simphiwe.footballapp.view.fragment.teams.teamslist.model


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("search")
    val search: String
)