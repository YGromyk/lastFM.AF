package com.gromyk.api.dtos


import com.google.gson.annotations.SerializedName

data class ArtistX(
    @SerializedName("image")
    var image: List<Image>,
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
)