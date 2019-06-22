package com.gromyk.api.dtos


import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("listeners")
    var listeners: String,
    @SerializedName("playcount")
    var playcount: String
)