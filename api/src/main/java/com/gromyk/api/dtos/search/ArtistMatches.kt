package com.gromyk.api.dtos.search


import com.google.gson.annotations.SerializedName
import com.gromyk.api.dtos.artist.Artist

data class ArtistMatches(
        @SerializedName("artist")
        var artist: List<Artist>?
)