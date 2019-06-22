package com.gromyk.api.dtos


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("size")
    var size: String,
    @SerializedName("#text")
    var text: String
)