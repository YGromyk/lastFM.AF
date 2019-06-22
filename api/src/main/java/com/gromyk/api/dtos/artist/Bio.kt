package com.gromyk.api.dtos.artist


import com.google.gson.annotations.SerializedName

data class Bio(
    @SerializedName("content")
    var content: String,
    @SerializedName("links")
    var links: Links,
    @SerializedName("published")
    var published: String,
    @SerializedName("summary")
    var summary: String
)