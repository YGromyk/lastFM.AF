package com.gromyk.persistence.album


import com.google.gson.annotations.SerializedName

data class Attr(
    @SerializedName("rank")
    var rank: String
)