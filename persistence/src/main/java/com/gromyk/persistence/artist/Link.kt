package com.gromyk.persistence.artist

import androidx.room.ColumnInfo

data class Link(
    @ColumnInfo(name = "href")
    var href: String,
    @ColumnInfo(name = "rel")
    var rel: String,
    @ColumnInfo(name = "#text")
    var text: String
)