package com.gromyk.persistence.artist

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("size")
    var size: String,
    @SerializedName("#text")
    var text: String
)