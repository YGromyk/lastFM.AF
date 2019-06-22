package com.gromyk.api.dtos.album


import com.google.gson.annotations.SerializedName
import com.gromyk.api.dtos.artist.Artist

data class Track(
    @SerializedName("artist")
    var artist: Artist,
    @SerializedName("duration")
    var duration: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("streamable")
    var streamable: Streamable,
    @SerializedName("url")
    var url: String
)