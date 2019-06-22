package com.gromyk.api.dtos


import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("bio")
    var bio: Bio,
    @SerializedName("image")
    var image: List<Image>,
    @SerializedName("mbid")
    var mbid: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("ontour")
    var ontour: String,
    @SerializedName("similar")
    var similar: Similar,
    @SerializedName("stats")
    var stats: Stats,
    @SerializedName("streamable")
    var streamable: String,
    @SerializedName("tags")
    var tags: Tags,
    @SerializedName("url")
    var url: String
)