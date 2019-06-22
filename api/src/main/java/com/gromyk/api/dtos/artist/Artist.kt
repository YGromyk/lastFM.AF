package com.gromyk.api.dtos.artist


import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("bio")
    var bio: Bio? = null,
    @SerializedName("image")
    var image: List<Image>? = null,
    @SerializedName("mbid")
    var mbid: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("ontour")
    var ontour: String? = null,
    @SerializedName("similar")
    var similar: Similar? = null,
    @SerializedName("stats")
    var stats: Stats? = null,
    @SerializedName("streamable")
    var streamable: String? = null,
    @SerializedName("tags")
    var tags: Tags? = null,
    @SerializedName("url")
    var url: String? = null
)