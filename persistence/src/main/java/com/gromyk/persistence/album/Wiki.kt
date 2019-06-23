package com.gromyk.persistence.album

import androidx.room.ColumnInfo


data class Wiki(
    @ColumnInfo(name = "content")
    var content: String,
    @ColumnInfo(name = "published")
    var published: String,
    @ColumnInfo(name = "summary")
    var summary: String
)