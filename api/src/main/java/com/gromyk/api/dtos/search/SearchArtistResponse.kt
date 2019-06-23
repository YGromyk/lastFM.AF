package com.gromyk.api.dtos.search


import com.google.gson.annotations.SerializedName

data class SearchArtistResponse(
        @SerializedName("results") var results: Results?
)