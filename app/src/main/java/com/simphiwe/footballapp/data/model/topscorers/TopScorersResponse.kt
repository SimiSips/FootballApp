package com.simphiwe.footballapp.data.model.topscorers


import com.google.gson.annotations.SerializedName
import com.simphiwe.footballapp.data.model.player.Paging
import com.simphiwe.footballapp.data.model.player.Parameters
import com.simphiwe.footballapp.data.model.player.Response

data class TopScorersResponse(
    @SerializedName("errors")
    val errors: List<Any>,
    @SerializedName("get")
    val get: String,
    @SerializedName("paging")
    val paging: Paging,
    @SerializedName("parameters")
    val parameters: Parameters,
    @SerializedName("response")
    val response: List<Response>,
    @SerializedName("results")
    val results: Int
)