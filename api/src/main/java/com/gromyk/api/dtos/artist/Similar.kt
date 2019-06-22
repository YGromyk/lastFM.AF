package com.gromyk.api.dtos.artist


import com.google.gson.annotations.SerializedName

data class Similar(
    @SerializedName("artist")
    var artist: List<OtherArtist>
)