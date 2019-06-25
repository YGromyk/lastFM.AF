package com.gromyk.api.dtos.album


import com.google.gson.annotations.SerializedName

data class Streamable(
    @SerializedName("fullTrack")
    var fullTrack: String,
    @SerializedName("#text")
    var text: String
)