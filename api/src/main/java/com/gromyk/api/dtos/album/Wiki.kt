package com.gromyk.api.dtos.album


import com.google.gson.annotations.SerializedName

data class Wiki(
    @SerializedName("content")
    var content: String,
    @SerializedName("published")
    var published: String,
    @SerializedName("summary")
    var summary: String
)