package com.gromyk.api.dtos.album


import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("track")
    var track: List<Track>?
)