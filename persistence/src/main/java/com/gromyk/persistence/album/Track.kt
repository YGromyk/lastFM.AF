package com.gromyk.persistence.album


import androidx.room.ColumnInfo
import com.gromyk.persistence.artist.Artist

data class Track(
    @ColumnInfo(name = "artist")
    var artist: Artist,
    @ColumnInfo(name = "duration")
    var duration: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "streamable")
    var streamable: Streamable,
    @ColumnInfo(name = "url")
    var url: String
)