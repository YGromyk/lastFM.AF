package com.gromyk.api.dtos

import com.google.gson.annotations.SerializedName

data class ArtistSearchResponse(
    @SerializedName("results") var artistSearchResult: ArtistSearchResult
)

data class ArtistSearchResult(
    @SerializedName("opensearch:totalResults") var totalResults: Int,
    @SerializedName("opensearch:startIndex") var startIndex: Int,
    @SerializedName("opensearch:itemsPerPage") var itemsPerPage: Int,
    @SerializedName("artistmatches") var artistMatches: MatchedArtist? = null
)

data class MatchedArtist(
    @SerializedName("artist") var artistMatches: List<Artist>? = null
)