package com.gromyk.api.dtos.album


import com.google.gson.annotations.SerializedName

data class Attr(
    @SerializedName("rank")
    var rank: String
)