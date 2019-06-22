package com.gromyk.api.dtos.artist


import com.google.gson.annotations.SerializedName

data class Tags(
    @SerializedName("tag")
    var tag: List<Tag>
)