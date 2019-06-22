package com.gromyk.api.dtos.artist


import com.google.gson.annotations.SerializedName

/**
 * Represents other artist with few details
 */
data class OtherArtist(
    @SerializedName("image")
    var image: List<Image>,
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
)