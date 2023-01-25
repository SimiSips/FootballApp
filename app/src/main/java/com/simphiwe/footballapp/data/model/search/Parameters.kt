package com.simphiwe.footballapp.data.model.search


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("search")
    val search: String
)