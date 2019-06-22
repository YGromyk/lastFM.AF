package com.gromyk.api.dtos.artist


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("href")
    var href: String,
    @SerializedName("rel")
    var rel: String,
    @SerializedName("#text")
    var text: String
)