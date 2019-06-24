package com.gromyk.persistence.album


import androidx.room.Ignore
import com.google.gson.annotations.SerializedName
import com.gromyk.persistence.artist.Artist

data class Track(
    @SerializedName("trackId")
    var trackId: Int? = null,
    @Ignore
    @SerializedName("artist")
    var artist: Artist?,
    @SerializedName("duration")
    var duration: String,
    @SerializedName("name")
    var name: String,
    @Ignore
    @SerializedName("streamable")
    var streamable: Streamable?,
    @SerializedName("url")
    var url: String
)