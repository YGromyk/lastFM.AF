package com.gromyk.persistence.album

import androidx.room.ColumnInfo


data class Streamable(
    @ColumnInfo(name = "fullTrack")
    var fullTrack: String,
    @ColumnInfo(name = "#text")
    var text: String
)