package com.gromyk.api.dtos.search


import com.google.gson.annotations.SerializedName

data class Results(
        @SerializedName("artistmatches")
        var artistMatches: ArtistMatches?,
        @SerializedName("opensearch:itemsPerPage")
        var opensearchItemsPerPage: String,
        @SerializedName("opensearch:Query")
        var openSearchQuery: OpenSearchQuery,
        @SerializedName("opensearch:startIndex")
        var opensearchStartIndex: String,
        @SerializedName("opensearch:totalResults")
        var opensearchTotalResults: String
)