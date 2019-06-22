package com.gromyk.api.dtos


import com.google.gson.annotations.SerializedName

data class Similar(
    @SerializedName("artist")
    var artist: List<ArtistX>
)