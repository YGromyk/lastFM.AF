package com.gromyk.api.dtos.artist


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("link")
    var link: Link
)