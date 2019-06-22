package com.gromyk.api.dtos.album


import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("album")
    var album: Album
)