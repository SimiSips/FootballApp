package com.simphiwe.footballapp.view.fragment.teams.teamslist.model


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("team")
    val team: Team,
    @SerializedName("venue")
    val venue: Venue
)