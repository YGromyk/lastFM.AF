package com.gromyk.api.dtos.artist


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
)