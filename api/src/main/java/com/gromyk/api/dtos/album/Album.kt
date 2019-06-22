package com.gromyk.api.dtos.album


import com.google.gson.annotations.SerializedName
import com.gromyk.api.dtos.artist.Image
import com.gromyk.api.dtos.artist.Tags

data class Album(
    @SerializedName("artist")
    var artist: String,
    @SerializedName("image")
    var image: List<Image>,
    @SerializedName("listeners")
    var listeners: String,
    @SerializedName("mbid")
    var mbid: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("playcount")
    var playcount: String,
    @SerializedName("tags")
    var tags: Tags,
    @SerializedName("tracks")
    var tracks: Tracks,
    @SerializedName("url")
    var url: String,
    @SerializedName("wiki")
    var wiki: Wiki
)