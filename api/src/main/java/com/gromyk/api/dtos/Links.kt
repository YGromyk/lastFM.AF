package com.gromyk.api.dtos


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("link")
    var link: Link
)