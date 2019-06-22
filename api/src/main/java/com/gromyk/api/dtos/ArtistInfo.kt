package com.gromyk.api.dtos


import com.google.gson.annotations.SerializedName

data class ArtistInfo(
    @SerializedName("artist")
    var artist: Artist
)