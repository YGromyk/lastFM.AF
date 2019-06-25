package com.gromyk.api.dtos.search


import com.google.gson.annotations.SerializedName

data class OpenSearchQuery(
        @SerializedName("role")
        var role: String,
        @SerializedName("searchTerms")
        var searchTerms: String,
        @SerializedName("startPage")
        var startPage: String,
        @SerializedName("#text")
        var text: String
)