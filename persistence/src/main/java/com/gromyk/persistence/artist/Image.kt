package com.gromyk.persistence.artist
import androidx.room.ColumnInfo

data class Image(
    @ColumnInfo(name = "size")
    var size: String,
    @ColumnInfo(name = "#text")
    var text: String
)