package com.gromyk.persistence.artist


import androidx.room.ColumnInfo

data class Bio(
    @ColumnInfo(name = "content")
    var content: String,
    @ColumnInfo(name = "links")
    var links: Links,
    @ColumnInfo(name = "published")
    var published: String,
    @ColumnInfo(name = "summary")
    var summary: String
)